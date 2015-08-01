package com.vach.cafe.server;

import com.vach.cafe.server.util.ObjectSupport;

public abstract class Command extends ObjectSupport
    implements com.vach.cafe.server.Message {

  public long id;

  public Command(long aggregateId) {
    this.id = aggregateId;
  }

}