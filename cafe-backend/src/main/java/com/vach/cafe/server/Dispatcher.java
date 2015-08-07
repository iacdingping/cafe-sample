package com.vach.cafe.server;


import com.vach.cafe.server.util.ICanLog;

import java.util.List;


public interface Dispatcher<T> extends ICanLog {

  /**
   * Dispatch an element to appropriate handler
   */
  void dispatch(T element);

  /**
   * Dispatch batch of elements to their handlers.
   */
  default void dispatch(List<T> elementBatch) {
    elementBatch.forEach(this::dispatch);
  }
}
