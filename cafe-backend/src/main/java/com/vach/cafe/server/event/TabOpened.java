package com.vach.cafe.server.event;

import com.vach.cafe.server.Event;

import java.util.UUID;

public class TabOpened extends Event {

  public int tableNumber;
  public String waiter;

  public TabOpened(UUID id, int tableNumber, String waiter) {
    super(id);
    this.tableNumber = tableNumber;
    this.waiter = waiter;
  }


}
