package com.vach.cafe;

import com.vach.cafe.util.ICanLog;

public interface Dispatcher<T> extends ICanLog{

  void dispatch(T element);
}
