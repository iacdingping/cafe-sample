package com.vach.cafe;

public abstract class Message {

  /**
   * Timestamp of event creation in nanoseconds
   */
  public long timestamp;

  public Message(long timestamp) {
    this.timestamp = timestamp;
  }

}
