package com.vach.cafe;

import com.vach.cafe.util.ICanLog;

/**
 * Each CommandHandler takes care of business logic of single type of Command, it validates incoming commands, and executes appropriate
 * public methods on Aggregates, no side effects shall be implemented in Command handlers (e.g. email shall be an event handler not a
 * command handler).
 */
public interface CommandHandler<T extends Command> extends Handler<T>, ICanLog {

  /**
   * Acceptable Command type (for indexing purposes)
   */
  Class<T> type();

  /**
   * Handling business logic of concrete Command type, shall never throw exception, instead outcome of processing shall be propagated
   * trough CallbackRegister.
   */
  void on(T command);
}
