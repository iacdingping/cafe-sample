package com.vach.cafe.server.event.bus;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.vach.cafe.server.Bus;
import com.vach.cafe.server.Event;

import java.util.concurrent.Executors;

public class EventBus implements Bus<Event> {

  private final Disruptor<EventHolder> disruptor;

  public EventBus() {
    disruptor = new Disruptor<>(
        EventHolder::new,
        1024,
        Executors.newCachedThreadPool(),
        ProducerType.MULTI,
        new BlockingWaitStrategy()
    );

//    disruptor.handleEventsWith(new EventDelegate(dispatcher));
    disruptor.start();
  }

  @Override
  public void publish(Event command) {
//    disruptor.publishEvent(command);
  }


  /**
   * Single cell of data in ring buffer
   */
  private class EventHolder {

    public String type;
    //  public byte[] data;
    // TODO serialization of events
    public com.vach.cafe.server.Event message;
  }

}
