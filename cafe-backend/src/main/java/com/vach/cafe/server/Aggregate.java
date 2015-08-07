package com.vach.cafe.server;


import com.vach.cafe.server.util.ICanLog;

import java.util.UUID;

public abstract class Aggregate implements IHandleCommand, IHandleEvent, ICanLog {

  protected UUID id;
  protected int version;

  /**
   * Unique id of an aggregate
   */
  public UUID id() {
    return id;
  }

  /**
   * Current version of aggregate (number of the latest event applied)
   */
  public int version() {
    return version;
  }
}