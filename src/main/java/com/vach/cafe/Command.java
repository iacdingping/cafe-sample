package com.vach.cafe;

public abstract class Command extends Message {

  /**
   * Id of the aggregate the change was applied to.
   */
  public long id;

  public Command(long id) {
    super(System.nanoTime());
    this.id = id;
  }
}
