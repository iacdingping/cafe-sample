package com.vach.cafe.server.util;

import java.util.concurrent.TimeUnit;

public class Util {

  // wtf (what a terrible failure) exceptions are never expected to happen, these are indicating implementation flaws

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

  public static <T> T wtf(Throwable exception, String message, Object... args) {
    throw new IllegalStateException(String.format(message, args), exception);
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
}
