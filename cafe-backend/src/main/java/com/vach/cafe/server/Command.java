package com.vach.cafe.server;

import com.vach.cafe.server.util.ObjectSupport;

import java.util.UUID;

public abstract class Command extends ObjectSupport implements Message {

  public UUID id;

  public Command(UUID aggregateId) {
    this.id = aggregateId;
  }

}