package com.vach.cafe;

import com.vach.cafe.util.ICanLog;

public interface EventHandler<T extends Event> extends Handler<T>, ICanLog{

  /**
   * Acceptable Event type
   */
  Class<T> type();

  /**
   * Handling business logic
   */
  void on(T event);
}
