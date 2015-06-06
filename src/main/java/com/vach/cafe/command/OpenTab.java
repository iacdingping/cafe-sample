package com.vach.cafe.command;

import com.google.common.base.Objects;

import com.vach.cafe.Command;

import static com.vach.cafe.encoder.TypeRegister.COMMAND_OPEN_TAB;

public class OpenTab extends Command {

  public int tableNumber;
  public String waiter;

  public OpenTab(long id, int tableNumber, String waiter) {
    super(id, COMMAND_OPEN_TAB);
    this.tableNumber = tableNumber;
    this.waiter = waiter;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    OpenTab that = (OpenTab) o;

    return Objects.equal(this.tableNumber, that.tableNumber) &&
           Objects.equal(this.waiter, that.waiter) &&
           Objects.equal(this.aggregateId, that.aggregateId) &&
           Objects.equal(this.type, that.type);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(tableNumber, waiter, aggregateId, type);
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
}
