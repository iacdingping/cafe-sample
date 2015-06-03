package com.vach.cafe.util;

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

}
