package com.vach.cafe.event.bus;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.vach.cafe.Bus;
import com.vach.cafe.Event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import static com.vach.cafe.util.Util.wtf;

@Component
public class EventBus implements Bus<Event> {

  private final Disruptor<EventHolder> disruptor;

  private final EventTranslatorOneArg<EventHolder, Event> commandToEventHolderTranslator = (holder, sequence, cmd) -> {
    holder.type = cmd.type();
    holder.message = cmd;
  };

  @Autowired
  public EventBus(EventDispatcher dispatcher){
    disruptor = new Disruptor<>(
        EventHolder::new,
        1024,
        Executors.newCachedThreadPool(),
        ProducerType.MULTI,
        new BlockingWaitStrategy()
    );

    disruptor.handleEventsWith(new EventDelegate(dispatcher));
    disruptor.start();
  }

  @Override
  public void publish(Event command) {
    disruptor.publishEvent(commandToEventHolderTranslator, command);
    wtf();
  }

  /**
   * Adapter for Dispatcher
   */
  private class EventDelegate implements EventHandler<EventHolder> {
    private final EventDispatcher dispatcher;
    private final List<Event> batch = new ArrayList<>();

    public EventDelegate(EventDispatcher dispatcher){
      this.dispatcher = dispatcher;
    }


    @Override
    public void onEvent(EventHolder holder, long sequence, boolean endOfBatch) throws Exception {
      if (endOfBatch) {
        if (batch.isEmpty()) {
          dispatcher.dispatch(holder.message);
        } else {
          dispatcher.dispatch(batch);
          batch.clear();
        }
      } else {
        batch.add(holder.message);
      }
    }
  }

  /**
   * Single cell of data in ring buffer
   */
  private class EventHolder {
    public String type;
    //  public byte[] data;
    // TODO serialization of events
    public Event message;
  }

}
