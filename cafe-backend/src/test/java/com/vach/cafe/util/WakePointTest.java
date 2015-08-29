package com.vach.cafe.util;


import org.junit.Test;

import static com.vach.cafe.util.Util.bearSleep;

public class WakePointTest implements ICanLog {

  @Test
  public void testSuccessEmpty() {
    WakePoint wakePoint = new WakePoint();
    completeWithSuccess(wakePoint, 2000);
    wakePoint.waitForResult(10000);
    report(wakePoint);
  }

  @Test
  public void testSuccessWithArgs() {
    WakePoint wakePoint = new WakePoint();
    completeWithSuccess(wakePoint, 2000, "someResult");
    wakePoint.waitForResult(10000);
    report(wakePoint);
  }

  @Test
  public void testFailure() {
    WakePoint wakePoint = new WakePoint();
    completeWithFailure(wakePoint, 2000);
    wakePoint.waitForResult(10000);
    report(wakePoint);
  }

  @Test//(expected = IllegalStateException.class)
  public void testTimeoutFailure() {
    WakePoint wakePoint = new WakePoint();
    completeWithFailure(wakePoint, 5000);
    wakePoint.waitForResult(2000);
    report(wakePoint);
    wakePoint.waitForResult(2000);
    report(wakePoint);
  }

  private void report(WakePoint result) {
    if (result.isSuccess()) {
      System.out.println("SUCCESS : " + ((result.success().isPresent()) ? "with args" : "no args"));
    } else {
      System.out.println("FAILURE : " + result.failure().get());
    }
  }

  void completeWithSuccess(WakePoint wakePoint, int timeout) {
    new Thread(() -> {
      bearSleep(timeout);
      wakePoint.complete();
    }).start();
  }

  void completeWithSuccess(WakePoint wakePoint, int timeout, Object... args) {
    new Thread(() -> {
      bearSleep(timeout);
      wakePoint.complete(args);
    }).start();
  }

  void completeWithFailure(WakePoint wakePoint, long timeout) {
    new Thread(() -> {
      bearSleep(timeout);
      wakePoint.complete(new IllegalStateException());
    }).start();
  }
}