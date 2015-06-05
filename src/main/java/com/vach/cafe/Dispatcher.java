package com.vach.cafe;

import com.vach.cafe.util.ICanLog;

import java.util.List;

public interface Dispatcher<T> extends ICanLog {

  void dispatch(T element);

  default void dispatch(List<T> elementBatch) {
    elementBatch.forEach(this::dispatch);
  }
}
