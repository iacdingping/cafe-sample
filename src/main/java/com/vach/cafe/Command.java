package com.vach.cafe;

import com.vach.cafe.util.CallbackRegister;

public abstract class Command implements Message {

  protected long aggregateId;
  protected String type;
  protected long timestamp;

  private transient final CallbackRegister callbackRegister = new CallbackRegister();

  protected Command(){}

  public Command(long aggregateId, String type) {
    this.aggregateId = aggregateId;
    this.timestamp = System.currentTimeMillis();
    this.type = type;
  }

  public CallbackRegister whenCompleted() {
    return this.callbackRegister;
  }

  public long timestamp() {
    return timestamp;
  }

  public String type() {
    return type;
  }
}