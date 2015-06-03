package com.vach.cafe;

public abstract class Event implements Message {

  protected long aggregateId;
  protected String type;
  protected long timestamp;

  protected Event(){}

  public Event(long aggregateId, String type) {
    this.aggregateId = aggregateId;
    this.timestamp = System.currentTimeMillis();
    this.type = type;
  }

  public long timestamp() {
    return timestamp;
  }

  public String type() {
    return type;
  }
}
