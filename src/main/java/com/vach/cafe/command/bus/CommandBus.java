package com.vach.cafe.command.bus;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.vach.cafe.Bus;
import com.vach.cafe.Command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;

import static com.vach.cafe.util.Util.wtf;


@Component
public class CommandBus implements Bus<Command>{

  private final Disruptor<Command> disruptor;

  @Autowired
  public CommandBus(CommandDispatcher dispatcher){
    disruptor = new Disruptor<>(
        wtf(), // TODO implement factory for commands
        1024,
        Executors.newCachedThreadPool(),
        ProducerType.MULTI,
        new BlockingWaitStrategy()
    );

    disruptor.handleEventsWith(dispatcher);
    disruptor.start();
  }

  @Override
  public void publish(Command message) {
    // TODO implement translation from object
    wtf();
  }

  @Override
  public void publish(String type, byte[] data){
    // TODO implement translation from blob
    wtf();
  }


}
