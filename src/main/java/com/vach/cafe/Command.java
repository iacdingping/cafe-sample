package com.vach.cafe;

import static com.vach.cafe.util.Util.log;

public abstract class Command extends Message {

  /**
   * Id of the aggregate the change was applied to.
   */
  public long id;
  private final CallbackRegister callbackRegister = new CallbackRegister();

  public Command(long id) {
    super(System.nanoTime());
    this.id = id;
  }

  public CallbackRegister whenCompleted() {
    return this.callbackRegister;
  }
}

class CallbackRegister {

  private boolean completed;
  private boolean isSuccess;
  private boolean someoneIsWaiting;

  private Object result;
  private Exception cause;

  private SuccessCallback onSuccess;
  private FailureCallback onFailure;

  public CallbackRegister onSuccess(SuccessCallback callable) {
    log("registering success callback");
    this.onSuccess = callable;
    return this;
  }

  public CallbackRegister onFailure(FailureCallback callable) {
    log("registering fail callback");
    onFailure = callable;
    return this;
  }

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
   * @param result optional return data
   */
  public synchronized void success(Object result) {

    // if no one is waiting for response
    if(!someoneIsWaiting) {
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
   * Complete with failure
   *
   * @param cause reason of failure
   */
  public synchronized void fail(Exception cause) {

    // if no one is waiting for response
    if(!someoneIsWaiting) {
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
