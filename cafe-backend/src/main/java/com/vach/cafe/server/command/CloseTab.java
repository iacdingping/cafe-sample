package com.vach.cafe.server.command;

import com.vach.cafe.server.Command;

public class CloseTab extends Command {

  public double amountPaid;

  public CloseTab(long id, double amountPaid) {
    super(id);
    this.amountPaid = amountPaid;
  }

}
