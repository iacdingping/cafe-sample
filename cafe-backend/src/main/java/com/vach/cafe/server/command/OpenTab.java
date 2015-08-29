package com.vach.cafe.server.command;

import com.vach.cafe.server.Command;

import java.util.UUID;

public class OpenTab extends Command {

  public int tableNumber;
  public String waiter;

  public OpenTab(UUID id, int tableNumber, String waiter) {
    super(id);
    this.tableNumber = tableNumber;
    this.waiter = waiter;
  }

  public OpenTab(int tableNumber, String waiter) {
    super(UUID.randomUUID());
    this.tableNumber = tableNumber;
    this.waiter = waiter;
  }
}
