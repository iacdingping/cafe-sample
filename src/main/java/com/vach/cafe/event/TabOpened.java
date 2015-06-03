package com.vach.cafe.event;

import com.vach.cafe.Event;

import static com.vach.cafe.encoder.TypeRegister.EVENT_TAB_OPENED;

public class TabOpened extends Event {

  public int tableNumber;
  public String waiter;

  public TabOpened(long id, int tableNumber, String waiter) {
    super(id, EVENT_TAB_OPENED);
    this.tableNumber = tableNumber;
    this.waiter = waiter;
  }

  @Override
  public String toString() {
    return String.format(
        "tabOpened { aggregateId : %s, tableNumber : %d, waiter : %s }",
        aggregateId,
        tableNumber,
        waiter
    );
  }
}
