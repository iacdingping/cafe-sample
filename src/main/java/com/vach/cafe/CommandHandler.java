package com.vach.cafe;

import com.vach.cafe.util.ICanLog;

public interface CommandHandler <T extends Command> extends Handler<T>, ICanLog{

  /**
   * Acceptable Command type
   */
  Class<T> type();

  /**
   * Handling business logic
   */
  void on(T command);
}
