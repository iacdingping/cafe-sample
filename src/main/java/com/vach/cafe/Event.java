package com.vach.cafe;

import com.vach.cafe.util.ObjectSupport;

public abstract class Event extends ObjectSupport implements Message {

  public long id;

  public Event(long aggregateId) {
    this.id = aggregateId;
  }

  
}
