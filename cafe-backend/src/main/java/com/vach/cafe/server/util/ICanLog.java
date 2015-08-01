package com.vach.cafe.server.util;

import static java.lang.String.format;
import static org.slf4j.LoggerFactory.getLogger;

public interface ICanLog {

  /**
   * Log INFO level message
   */
  default void info(String msg) {
    getLogger(this.getClass()).info(msg);
  }

  /**
   * Log INFO level message with exception
   */
  default void info(String msg, Throwable e) {
    getLogger(this.getClass()).info(msg, e);
  }

  /**
   * Log INFO level formatted message
   */
  default void info(String msg, Object... arguments) {
    getLogger(this.getClass()).info(format(msg, arguments));
  }

  /**
   * Log INFO level formatted message with exception
   */
  default void info(String msg, Throwable e, Object... arguments) {
    getLogger(this.getClass()).info(format(msg, arguments), e);
  }

  // debug

  /**
   * Log DEBUG level message
   */
  default void debug(String msg) {
    getLogger(this.getClass()).debug(msg);
  }

  /**
   * Log DEBUG level message with exception
   */
  default void debug(String msg, Throwable e) {
    getLogger(this.getClass()).debug(msg, e);
  }

  /**
   * Log DEBUG level formatted message
   */
  default void debug(String msg, Object... arguments) {
    getLogger(this.getClass()).debug(format(msg, arguments));
  }

  /**
   * Log DEBUG level formatted message with exception
   */
  default void debug(String msg, Throwable e, Object... arguments) {
    getLogger(this.getClass()).debug(format(msg, arguments), e);
  }

  // warn

  /**
   * Log WARN level message
   */
  default void warn(String msg) {
    getLogger(this.getClass()).warn(msg);
  }

  /**
   * Log WARN level message with exception
   */
  default void warn(String msg, Throwable e) {
    getLogger(this.getClass()).warn(msg, e);
  }

  /**
   * Log WARN level formatted message
   */
  default void warn(String msg, Object... arguments) {
    getLogger(this.getClass()).warn(format(msg, arguments));
  }

  /**
   * Log WARN level formatted message with exception
   */
  default void warn(String msg, Throwable e, Object... arguments) {
    getLogger(this.getClass()).warn(format(msg, arguments), e);
  }

  // error

  /**
   * Log ERROR level message
   */
  default void error(String msg) {
    getLogger(this.getClass()).error(msg);
  }

  /**
   * Log ERROR level message with exception
   */
  default void error(String msg, Throwable e) {
    getLogger(this.getClass()).error(msg, e);
  }

  /**
   * Log ERROR level formatted message
   */
  default void error(String msg, Object... arguments) {
    getLogger(this.getClass()).error(format(msg, arguments));
  }

  /**
   * Log ERROR level formatted message with exception
   */
  default void error(String msg, Throwable e, Object... arguments) {
    getLogger(this.getClass()).error(format(msg, arguments), e);
  }

  // trace

  /**
   * Log TRACE level message
   */
  default void trace(String msg) {
    getLogger(this.getClass()).trace(msg);
  }

  /**
   * Log TRACE level message with exception
   */
  default void trace(String msg, Throwable e) {
    getLogger(this.getClass()).trace(msg, e);
  }

  /**
   * Log TRACE level formatted message
   */
  default void trace(String msg, Object... arguments) {
    getLogger(this.getClass()).trace(format(msg, arguments));
  }

  /**
   * Log TRACE level formatted message with exception
   */
  default void trace(String msg, Throwable e, Object... arguments) {
    getLogger(this.getClass()).trace(format(msg, arguments), e);
  }


}
