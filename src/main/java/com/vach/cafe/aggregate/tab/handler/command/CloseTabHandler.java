package com.vach.cafe.aggregate.tab.handler.command;

import com.vach.cafe.CommandHandler;
import com.vach.cafe.command.CloseTab;

import org.springframework.stereotype.Component;

@Component
public class CloseTabHandler implements CommandHandler<CloseTab> {

  @Override
  public Class<CloseTab> type() {
    return CloseTab.class;
  }

  @Override
  public void on(CloseTab command) {
    info("handling : %s", command);

    command.progress().success();
  }
}
