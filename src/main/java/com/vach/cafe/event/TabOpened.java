package com.vach.cafe.event;

import com.vach.cafe.Event;

public class TabOpened extends Event {

  public int tableNumber;
  public String waiter;

  public TabOpened(long id, int tableNumber, String waiter) {
    super(id);
    this.tableNumber = tableNumber;
    this.waiter = waiter;
  }

  @Override
  public String toString() {
    return String.format(
        "tabOpened { id : %s, tableNumber : %d, waiter : %s }",
        id,
        tableNumber,
        waiter
    );
  }
}
