package com.vach.cafe;

import com.vach.cafe.util.ICanLog;

public interface EventHandler<T extends Event> extends ICanLog {

  void apply(T event);

}
