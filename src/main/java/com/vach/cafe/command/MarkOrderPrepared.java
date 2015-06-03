package com.vach.cafe.command;

import com.vach.cafe.Command;

import static com.vach.cafe.encoder.TypeRegister.COMMAND_MARK_ORDER_PREPARED;

public class MarkOrderPrepared extends Command {

  public MarkOrderPrepared(long id) {
    super(id, COMMAND_MARK_ORDER_PREPARED);
  }

}
