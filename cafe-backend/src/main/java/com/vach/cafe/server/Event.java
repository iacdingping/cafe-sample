package com.vach.cafe.server;

import com.vach.cafe.util.ObjectSupport;

import java.util.UUID;

public abstract class Event extends ObjectSupport implements Message {

  public UUID id;

  public Event(UUID aggregateId) {
    this.id = aggregateId;
  }
}
