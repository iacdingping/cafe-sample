package com.vach.cafe;

import com.vach.cafe.util.ObjectSupport;

public abstract class Command extends ObjectSupport implements Message {

  public long id;

  public Command(long aggregateId) {
    this.id = aggregateId;
  }

}