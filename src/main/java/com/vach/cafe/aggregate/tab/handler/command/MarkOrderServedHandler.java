package com.vach.cafe.aggregate.tab.handler.command;

import com.vach.cafe.CommandHandler;
import com.vach.cafe.command.MarkOrderServed;

import org.springframework.stereotype.Component;

@Component
public class MarkOrderServedHandler implements CommandHandler<MarkOrderServed> {

  @Override
  public Class<MarkOrderServed> type() {
    return MarkOrderServed.class;
  }

  @Override
  public void on(MarkOrderServed command) {
    info("handling : %s", command);
  }
}
