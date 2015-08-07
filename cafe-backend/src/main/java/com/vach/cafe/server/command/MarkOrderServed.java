package com.vach.cafe.server.command;

import com.vach.cafe.server.Command;

import java.util.UUID;

public class MarkOrderServed extends Command {

  public MarkOrderServed(UUID id) {
    super(id);
  }

}
