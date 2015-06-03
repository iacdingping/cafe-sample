package com.vach.cafe;

public abstract class Event extends Message {

  /**
   * Id of the aggregate the change was applied to.
   */
  public long id;

  public Event(long id){
    super(System.nanoTime());
    this.id = id;
  }
}
