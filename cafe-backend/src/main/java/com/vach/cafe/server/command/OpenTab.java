package com.vach.cafe.server.command;

import com.vach.cafe.server.Command;

public class OpenTab extends Command {

  public int tableNumber;
  public String waiter;

  public OpenTab(long id, int tableNumber, String waiter) {
    super(id);
    this.tableNumber = tableNumber;
    this.waiter = waiter;
  }

}
