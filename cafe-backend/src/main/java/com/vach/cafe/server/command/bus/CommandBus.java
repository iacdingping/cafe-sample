package com.vach.cafe.server.command.bus;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.vach.cafe.server.Bus;
import com.vach.cafe.server.Command;
import com.vach.cafe.server.Dispatcher;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import static com.vach.cafe.server.util.Util.wtf;

public class CommandBus implements Bus<Command> {

  private final Disruptor<CommandHolder> disruptor;

  private final EventTranslatorOneArg<CommandHolder, Command>
      commandToCommandHolderTranslator =
      (holder, sequence, cmd) -> {
        holder.message = cmd;
      };

  public CommandBus(com.vach.cafe.server.Dispatcher<Command> dispatcher) {
    disruptor = new Disruptor<>(
        CommandHolder::new,
        1024,
        Executors.newCachedThreadPool(),
        ProducerType.MULTI,
        new BlockingWaitStrategy()
    );

    disruptor.handleEventsWith(new CommandDelegate(dispatcher));
    disruptor.start();
  }

  @Override
  public void publish(Command command) {
    disruptor.publishEvent(commandToCommandHolderTranslator, command);
    wtf();
  }

  /**
   * Adapter for Dispatcher
   */
  private class CommandDelegate implements EventHandler<CommandHolder> {

    private final Dispatcher<Command> dispatcher;
    private final List<Command> batch = new ArrayList<>();

    public CommandDelegate(Dispatcher<Command> dispatcher) {
      this.dispatcher = dispatcher;
    }


    @Override
    public void onEvent(CommandHolder holder, long sequence, boolean endOfBatch) throws Exception {
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
  private class CommandHolder {

    public String type;
    //  public byte[] data; // Serialized Command (which when parsed will create Command instance)
    // TODO serialization of commands
    public Command message;
  }

}


