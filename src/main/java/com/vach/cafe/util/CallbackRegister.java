package com.vach.cafe.util;

import static com.vach.cafe.util.Util.log;

/**
 * Reusable callback holder for single producer to subscribe for completion.
 * After completion it automatically resets to blank state.
 *
 * When a Command is published by producer, it will be completed by handler using CallbackRegister
 * which will continue execution of callback on producer thread (thus not blocking handlers).
 */
public class CallbackRegister {

  private boolean completed;
  private boolean isSuccess;
  private boolean someoneIsWaiting;

  private Object result;
  private Exception cause;

  private SuccessCallback onSuccess;
  private FailureCallback onFailure;

  /**
   * Callback to be executed if command succeeds
   */
  public CallbackRegister onSuccess(SuccessCallback callable) {
    log("registering success callback");
    this.onSuccess = callable;
    return this;
  }

  /**
   * Callback to be executed if command fails
   */
  public CallbackRegister onFailure(FailureCallback callable) {
    log("registering fail callback");
    onFailure = callable;
    return this;
  }

  /**
   * Puts thread in WAIT state till command is explicitly completed trough either success(..) or fail(..)
   */
  public synchronized void waitForResult() {

    try {

      // wait for completion
      while (!completed) {
        log("waiting for notification");
        someoneIsWaiting = true;
        this.wait();
      }

      if (isSuccess) {
        log("executing on success");
        onSuccess.run(result);
      } else {
        log("executing on failure");
        onFailure.run(cause);
      }

    } catch (InterruptedException e) {
      log("got interrupted");
      onFailure.run(e);
    }

    reset();
  }

  /**
   * Complete with success
   *
   * @param result optional return data (can be null)
   */
  public synchronized void success(Object result) {

    // if no one is waiting for response
    if (!someoneIsWaiting) {
      // ignore the call
      log("ignoring the call");
      return;
    }

    log("completing with success");
    this.completed = true;
    this.isSuccess = true;
    this.result = result;
    this.notify();
  }

  /**
   * Complete with success
   */
  public synchronized void success(){
    // if no one is waiting for response
    if (!someoneIsWaiting) {
      // ignore the call
      log("ignoring the call");
      return;
    }

    log("completing with success");
    this.completed = true;
    this.isSuccess = true;
    this.notify();
  }

  /**
   * Complete with failure
   *
   * @param cause reason of failure
   */
  public synchronized void fail(Exception cause) {

    // if no one is waiting for response
    if (!someoneIsWaiting) {
      // ignore the call
      log("ignoring the call");
      return;
    }

    log("completing with failure");
    this.completed = true;
    this.isSuccess = false;
    this.cause = cause;
    this.notify();
  }

  private void reset() {
    log("resetting the state");
    completed = false;
    isSuccess = false;

    result = null;
    cause = null;

    onSuccess = null;
    onFailure = null;

    someoneIsWaiting = false;
  }
}

@FunctionalInterface
interface SuccessCallback {

  void run(Object result);
}

@FunctionalInterface
interface FailureCallback {

  void run(Exception cause);
}
