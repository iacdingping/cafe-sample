package com.vach.cafe.util;

import java.util.concurrent.TimeUnit;

import static com.vach.cafe.util.Validator.allNotNull;
import static com.vach.cafe.util.Validator.notNullOrEmpty;

public class Util {
  // wtf exceptions must never happen

  public static <T> T wtf() {
    throw new IllegalStateException();
  }

  public static <T> T wtf(String message) {
    throw new IllegalStateException(message);
  }

  public static <T> T wtf(String message, Object... args) {
    throw new IllegalStateException(String.format(message, args));
  }

  public static <T> T wtf(Throwable exception) {
    throw new IllegalStateException(exception);
  }

  public static <T> T wtf(Throwable exception, String message) {
    throw new IllegalStateException(message, exception);
  }

  // concurrency

  public static void bearSleep(long millis) {
    try {
      TimeUnit.MILLISECONDS.sleep(millis);
    } catch (InterruptedException ignored) {
    }
  }

  // unchecked cast

  @SuppressWarnings("unchecked")
  public static <T> T cast(Object object) {
    try {
      return (T) object;
    } catch (ClassCastException e) {
      throw new IllegalStateException("unexpected cast", e);
    }
  }

  // printing

  public static void log(String msg) {
    assert notNullOrEmpty(msg);
    System.out.printf("%s : %s\n", Thread.currentThread().getName(), msg);
  }

  public static void logf(String format, Object... args) {
    assert notNullOrEmpty(format) && allNotNull(args);
    System.out.printf("%s : %s\n", Thread.currentThread().getName(), String.format(format, args));
  }

}
