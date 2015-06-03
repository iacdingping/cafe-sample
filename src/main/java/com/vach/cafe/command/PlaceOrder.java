package com.vach.cafe.command;

import com.vach.cafe.Command;

import static com.vach.cafe.encoder.TypeRegister.COMMAND_PLACE_ORDER;

public class PlaceOrder extends Command {

  public PlaceOrder(long id) {
    super(id, COMMAND_PLACE_ORDER);
  }

}
