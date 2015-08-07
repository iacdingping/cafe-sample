package com.vach.cafe.server.command;


import com.vach.cafe.server.Command;

import java.util.UUID;

public class MarkOrderPrepared extends Command {

  public MarkOrderPrepared(UUID id) {
    super(id);
  }

}
