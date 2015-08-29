package com.vach.cafe.util;

/**
 * Reusable callback holder for single producer to subscribe for completion. After completion it automatically resets to blank state.
 *
 * When a Command is published by producer, it will be completed by handler using CallbackRegister which will continue execution of
 * callback on producer thread (thus not blocking handlers).
 *
 * TODO improve and support multiple callbacks
 */
@Deprecated
public class CallbackRegister implements ICanLog {

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
    debug("registering success callback");
    this.onSuccess = callable;
    return this;
  }

  /**
   * Callback to be executed if command fails
   */
  public CallbackRegister onFailure(FailureCallback callable) {
    debug("registering fail callback");
    onFailure = callable;
    return this;
  }

  /**
   * Puts thread in WAIT state till command is explicitly completed trough either success(..) or fail(..), after its completed waiting
   * thread will execute the callback.
   *
   * IMPORTANT : all command handlers shall complete command.
   */
  public synchronized void waitForResult() {
    try {

      // wait for completion
      while (!completed) {
        debug("waiting for notification");
        someoneIsWaiting = true;
        this.wait();
      }

      if (isSuccess) {
        if (onSuccess != null) {
          debug("executing on success");
          onSuccess.run(result);
        } else {
          debug("onSuccess not specified");
        }
      } else {
        if (onFailure != null) {
          debug("executing on failure");
          onFailure.run(cause);
        } else {
          debug("onFailure not specified");
        }
      }

    } catch (InterruptedException e) {
      debug("got interrupted");
      onFailure.run(e);
    }

    reset();
  }

  /**
   * Complete with success
   *
   * @param result optional return data (can be null)
   */
  public synchronized void success(Object... result) {
    // if a thread is waiting for a response
    if (someoneIsWaiting) {
      debug("notifying waiting thread");
      this.completed = true;
      this.isSuccess = true;
      this.result = result;
      this.notify();
    }

    // if callback is specified
    else if (onSuccess != null) {
      debug("executing without notify");
      onSuccess.run(result);
      reset();
    }

    // ignore the call
    else {
      debug("ignoring the call");
    }
  }

  /**
   * Complete with success
   */
  public synchronized void success() {
    // if a thread is waiting for a response
    if (someoneIsWaiting) {
      debug("notifying waiting thread");
      this.completed = true;
      this.isSuccess = true;
      this.notify();
    }

    // if callback is specified
    else if (onSuccess != null) {
      debug("executing without notify");
      onSuccess.run();
      reset();
    }

    // ignore the call
    else {
      debug("ignoring the call");
    }
  }

  /**
   * Complete with failure
   *
   * @param cause reason of failure
   */
  public synchronized void fail(Exception cause) {
    // if a thread is waiting for a response
    if (someoneIsWaiting) {
      debug("notifying waiting thread");
      this.completed = true;
      this.isSuccess = false;
      this.cause = cause;
      this.notify();
    }

    // if callback is specified
    else if (onFailure != null) {
      debug("executing without notify");
      onFailure.run(cause);
      reset();
    }

    // ignore the call
    else {
      debug("ignoring the call");
    }
  }

  private void reset() {
    debug("resetting the state");
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

    void run(Object... results);
  }

  @FunctionalInterface
  public interface FailureCallback {

    void run(Exception cause);
  }
}

