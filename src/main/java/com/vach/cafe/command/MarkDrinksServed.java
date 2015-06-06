package com.vach.cafe.command;

import com.vach.cafe.Command;

import static com.vach.cafe.encoder.TypeRegister.COMMAND_MARK_DRINKS_SERVED;

public class MarkDrinksServed extends Command {

  public MarkDrinksServed(long id) {
    super(id, COMMAND_MARK_DRINKS_SERVED);
  }

}
