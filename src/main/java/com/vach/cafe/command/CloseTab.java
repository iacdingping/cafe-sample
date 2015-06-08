package com.vach.cafe.command;

import com.vach.cafe.Command;

public class CloseTab extends Command {

  public double amountPaid;

  public CloseTab(long id, double amountPaid) {
    super(id);
    this.amountPaid = amountPaid;
  }

}
