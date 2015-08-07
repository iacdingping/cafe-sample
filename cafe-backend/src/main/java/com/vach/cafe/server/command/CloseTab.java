package com.vach.cafe.server.command;

import com.vach.cafe.server.Command;

import java.util.UUID;

public class CloseTab extends Command {

  public double amountPaid;

  public CloseTab(UUID id, double amountPaid) {
    super(id);
    this.amountPaid = amountPaid;
  }

}
