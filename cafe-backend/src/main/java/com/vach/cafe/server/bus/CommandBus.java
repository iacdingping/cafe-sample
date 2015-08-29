package com.vach.cafe.server.bus;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.EventTranslatorTwoArg;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.vach.cafe.server.Bus;
import com.vach.cafe.server.Message;

import java.util.concurrent.Executors;

public class CommandBus implements Bus {

  private final Disruptor<MessageWrapper> disruptor;

  private final EventTranslatorTwoArg<MessageWrapper, String, Object>
      objectTranslator = (holder, sequence, type, object) -> {
    holder.type = type;
    holder.object = object;
  };

  private final EventTranslatorTwoArg<MessageWrapper, String, byte[]>
      blobTranslator = (holder, sequence, type, blob) -> {
    holder.type = type;
    holder.blob = blob;
  };

  public CommandBus() {
    disruptor = new Disruptor<>(
        MessageWrapper::new,
        1024,
        Executors.newCachedThreadPool(),
        ProducerType.MULTI,
        new BlockingWaitStrategy()
    );

//    disruptor.handleEventsWith();
    disruptor.start();
  }

  @Override
  public void publish(String type, Message message) {
    disruptor.getRingBuffer().publishEvent(objectTranslator, type, message);
  }

  @Override
  public void publish(String type, byte[] blob) {
    disruptor.getRingBuffer().publishEvent(blobTranslator, type, blob);
  }
}


