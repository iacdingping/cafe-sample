package com.vach.cafe.util;

/**
 * Reusable callback holder for single producer to subscribe for completion. After completion it automatically resets to blank state.
 *
 * When a Command is published by producer, it will be completed by handler using CallbackRegister which will continue execution of
 * callback on producer thread (thus not blocking handlers).
 *
 * TODO improve and support multiple callbacks
 */
public class CallbackRegister implements ICanLog{

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
    info("registering success callback");
    this.onSuccess = callable;
    return this;
  }

  /**
   * Callback to be executed if command fails
   */
  public CallbackRegister onFailure(FailureCallback callable) {
    info("registering fail callback");
    onFailure = callable;
    return this;
  }

  /**
   * Puts thread in WAIT state till command is explicitly completed trough either success(..) or fail(..) IMPORTANT : all command
   * handlers shall complete command.
   */
  public synchronized void waitForResult() {

    try {

      // wait for completion
      while (!completed) {
        info("waiting for notification");
        someoneIsWaiting = true;
        this.wait();
      }

      if (isSuccess) {
        if (onSuccess != null) {
          info("executing on success");
          onSuccess.run(result);
        } else {
          info("onSuccess not specified");
        }
      } else {
        if (onFailure != null) {
          info("executing on failure");
          onFailure.run(cause);
        } else {
          info("onFailure not specified");
        }
      }

    } catch (InterruptedException e) {
      info("got interrupted");
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
      info("ignoring the call");
      return;
    }

    info("completing with success");
    this.completed = true;
    this.isSuccess = true;
    this.result = result;
    this.notify();
  }

  /**
   * Complete with success
   */
  public synchronized void success() {
    // if no one is waiting for response
    if (!someoneIsWaiting) {
      // ignore the call
      info("ignoring the call");
      return;
    }

    info("completing with success");
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
      info("ignoring the call");
      return;
    }

    info("completing with failure");
    this.completed = true;
    this.isSuccess = false;
    this.cause = cause;
    this.notify();
  }

  private void reset() {
    info("resetting the state");
    completed = false;
    isSuccess = false;

    result = null;
    cause = null;

    onSuccess = null;
    onFailure = null;

    someoneIsWaiting = false;
  }

  @FunctionalInterface
  public interface SuccessCallback {

    void run(Object result);
  }

  @FunctionalInterface
  public interface FailureCallback {

    void run(Exception cause);
  }
}

