package com.vach.cafe.command;

import com.vach.cafe.Command;

import static com.vach.cafe.encoder.TypeRegister.COMMAND_MARK_ORDER_SERVED;

public class MarkOrderServed extends Command {

  public MarkOrderServed(long id) {
    super(id, COMMAND_MARK_ORDER_SERVED);
  }

}
