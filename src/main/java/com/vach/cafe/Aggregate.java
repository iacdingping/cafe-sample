package com.vach.cafe;


import com.vach.cafe.util.ICanLog;

public abstract class Aggregate implements IHandleCommand, IHandleEvent, ICanLog {

  protected long id;
  protected int version;

  /**
   * Unique id of an aggregate
   */
  public long id() {
    return id;
  }

  /**
   * Current version of aggregate (number of the latest event applied)
   */
  public int version() {
    return version;
  }
}