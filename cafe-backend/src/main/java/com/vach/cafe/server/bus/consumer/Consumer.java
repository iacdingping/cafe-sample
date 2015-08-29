package com.vach.cafe.server.bus.consumer;

import com.lmax.disruptor.EventHandler;
import com.vach.cafe.server.bus.MessageWrapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Consumer implements EventHandler<MessageWrapper> {

  private final List<MessageWrapper> batch = new ArrayList<>();

  @Override
  public void onEvent(MessageWrapper message, long sequence, boolean endOfBatch) throws Exception {
      if (endOfBatch) {
        // consuming single element
        if (batch.isEmpty()) {
          consume(message);
        }

        // consuming batch
        else {
          try {
            consume(batch);
          }finally {
            batch.clear();
          }
        }
      }

      // batch aggregation
      else {
        batch.add(message);
      }
  }

  /**
   * Process single message
   */
  protected abstract void consume(MessageWrapper element);

  /**
   * Default implementation of batch processing, if necessary this method shall be overridden
   */
  protected void consume(Collection<MessageWrapper> batch){
    batch.forEach(this::consume);
  }
}