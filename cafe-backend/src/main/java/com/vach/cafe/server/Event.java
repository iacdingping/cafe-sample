package com.vach.cafe.server;

import com.vach.cafe.server.util.ObjectSupport;

public abstract class Event extends ObjectSupport implements Message {

  public long id;

  public Event(long aggregateId) {
    this.id = aggregateId;
  }

  
}
