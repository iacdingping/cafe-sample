package com.vach.cafe;

import com.vach.cafe.util.CallbackRegister;

public abstract class Command implements Message {

  protected long aggregateId;
  protected String type;
  protected long timestamp;

  CallbackRegister callbackRegister;

  protected Command() {
  }

  public Command(long aggregateId, String type) {
    this.aggregateId = aggregateId;
    this.timestamp = System.currentTimeMillis();
    this.type = type;
  }

  public CallbackRegister progress() {
    if (callbackRegister == null) {
      callbackRegister = new CallbackRegister();
    }
    return this.callbackRegister;
  }

  public long aggregateId() {
    return aggregateId;
  }

  public long timestamp() {
    return timestamp;
  }

  public String type() {
    return type;
  }


}