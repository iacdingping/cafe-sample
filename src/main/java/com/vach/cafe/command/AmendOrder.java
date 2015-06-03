package com.vach.cafe.command;

import com.vach.cafe.Command;
import com.vach.cafe.encoder.TypeRegister;

public class AmendOrder extends Command {

  public AmendOrder(long id) {
    super(id, TypeRegister.COMMAND_AMEND_ORDER);
  }
}
