package com.vach.cafe.command;

import com.vach.cafe.Command;


public class OpenTab extends Command {

  public int tableNumber;
  public String waiter;

  public OpenTab(long id, int tableNumber, String waiter) {
    super(id);
    this.tableNumber = tableNumber;
    this.waiter = waiter;
  }

}
