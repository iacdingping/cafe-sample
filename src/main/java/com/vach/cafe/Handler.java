package com.vach.cafe;

import com.vach.cafe.util.ICanLog;

public interface Handler<T> extends ICanLog {

  /**
   * Acceptable Message type
   */
  Class<T> type();

  /**
   * Handling business logic
   */
  void on(T message);
}
