package com.vach.cafe;

public abstract class Command implements Message {

  public long id;

  public Command(long aggregateId) {
    this.id = aggregateId;
  }

}