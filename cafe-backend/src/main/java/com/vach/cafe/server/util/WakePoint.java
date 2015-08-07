package com.vach.cafe.server.util;

import java.util.Optional;
import java.util.concurrent.TimeoutException;

import static com.vach.cafe.server.util.Util.cast;

/**
 * WakePoint is designed to block producer thread wile consumer sets the outcome.
 * This uses wait/notify mechanism. Producer thread will block waiting for the
 * consumers to complete the request and set the result. Instance of WakePoint
 * is not reusable, once completed it cannot be modified.
 */
public class WakePoint implements ICanLog {
  boolean isComplete;
  boolean isSuccess;
  Optional<Object> success = Optional.empty();
  Optional<Exception> failure = Optional.empty();

  public boolean isComplete(){
    return isComplete;
  }

  public boolean isSuccess() {
    return isSuccess;
  }

  public <T> Optional<T> success() {
    return cast(success);
  }

  public Optional<Exception> failure() {
    return failure;
  }

  /**
   * This method shall be called from producer thread, and will block till,
   * result is completed with appropriate method or timeout is reached.
   */
  public synchronized void waitForResult(long timeout) {
    if(isComplete) return;

    try {
      debug("waiting for completion");
      this.wait(timeout);

      if (isComplete) {
        debug("completed");
      } else {
        debug("timed out");
        this.isComplete = true;
        this.isSuccess = false;
        this.failure = Optional.of(new TimeoutException());
      }
    } catch (InterruptedException e) {
      debug("got interrupted from timeout returning result");
      complete(e);
    }
  }


  public synchronized void complete(Object... args) {
    if(isComplete) return;

    debug("completing with success with result");
    this.isSuccess = true;
    this.success = Optional.of(args);
    this.isComplete = true;
    this.notify();
  }

  public synchronized void complete() {
    if(isComplete) return;

    debug("completing with success without result");
    this.isSuccess = true;
    this.isComplete = true;
    this.notify();
  }

  public synchronized void complete(Exception cause) {
    if(isComplete) return;

    debug("completing with failure");
    this.isSuccess = false;
    this.failure = Optional.of(cause);
    this.isComplete = true;
    this.notify();
  }

}

