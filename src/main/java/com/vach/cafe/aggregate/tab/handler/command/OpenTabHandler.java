package com.vach.cafe.aggregate.tab.handler.command;

import com.vach.cafe.CommandHandler;
import com.vach.cafe.command.OpenTab;

import org.springframework.stereotype.Component;

@Component
public class OpenTabHandler implements CommandHandler<OpenTab> {

  @Override
  public Class<OpenTab> type() {
    return OpenTab.class;
  }

  @Override
  public void on(OpenTab command) {
    info("handling : %s", command);
  }
}
