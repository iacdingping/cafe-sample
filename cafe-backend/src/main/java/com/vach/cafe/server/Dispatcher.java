package com.vach.cafe.server;


import com.vach.cafe.server.util.ICanLog;

import java.util.List;

public interface Dispatcher<T> extends ICanLog {

  void dispatch(T element);

  default void dispatch(List<T> elementBatch) {
    elementBatch.forEach(this::dispatch);
  }
}
