package com.vach.cafe;

import com.vach.cafe.util.ICanLog;

import java.util.List;

import static com.vach.cafe.util.Util.wtf;

public interface Dispatcher<T> extends ICanLog{

  void dispatch(T element);

  default void dispatch(List<T> elementBatch){

    // todo depending on size of the batch this can process messages in parallel
    // for example fork processing by aggregate (if aggregates do not depend on each other)

    wtf("batch processing not implemented");
  }
}
