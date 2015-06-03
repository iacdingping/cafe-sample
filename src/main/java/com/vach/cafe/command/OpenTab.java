package com.vach.cafe.command;

import com.google.common.base.Objects;

import com.vach.cafe.Command;

public class OpenTab extends Command {

  public int tableNumber;
  public String waiter;

  public OpenTab(long id, int tableNumber, String waiter) {
    super(id);
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
           Objects.equal(this.id, that.id) &&
           Objects.equal(this.timestamp, that.timestamp);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(tableNumber, waiter, id, timestamp);
  }

  @Override
  public String toString() {
    return String.format(
        "openTab { id : %s, tableNumber : %d, waiter : %s}",
        id,
        tableNumber,
        waiter
    );
  }
}
