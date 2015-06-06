package com.vach.cafe.event;

import com.google.common.base.Objects;

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
        "%s { id : '%d', tableNumber : '%d', waiter : '%s'}",
        type,
        aggregateId,
        tableNumber,
        waiter
    );
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;

    TabOpened that = (TabOpened) o;

    return Objects.equal(this.tableNumber, that.tableNumber) &&
           Objects.equal(this.waiter, that.waiter) &&
           Objects.equal(this.aggregateId, that.aggregateId) &&
           Objects.equal(this.type, that.type);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(tableNumber, waiter, aggregateId, type);
  }
}
