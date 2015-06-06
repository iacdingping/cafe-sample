package com.vach.cafe;

public abstract class Event implements Message {

  protected long id;

  public Event(long aggregateId) {
    this.id = aggregateId;
  }

  
}
