package com.vach.cafe.command;

import com.vach.cafe.Command;

import static com.vach.cafe.encoder.TypeRegister.COMMAND_CLOSE_TAB;

public class CloseTab extends Command {

  public CloseTab(long id) {
    super(id, COMMAND_CLOSE_TAB);
  }

}
