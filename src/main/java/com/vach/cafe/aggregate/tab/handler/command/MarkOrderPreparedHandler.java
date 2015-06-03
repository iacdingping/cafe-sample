package com.vach.cafe.aggregate.tab.handler.command;

import com.vach.cafe.CommandHandler;
import com.vach.cafe.command.MarkOrderPrepared;

import org.springframework.stereotype.Component;

@Component
public class MarkOrderPreparedHandler implements CommandHandler<MarkOrderPrepared> {

  @Override
  public Class<MarkOrderPrepared> type() {
    return MarkOrderPrepared.class;
  }

  @Override
  public void on(MarkOrderPrepared command) {
    info("handling : %s", command);
  }
}
