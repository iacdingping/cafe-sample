package com.vach.cafe.aggregate.tab.handler.command;

import com.vach.cafe.CommandHandler;
import com.vach.cafe.command.MarkDrinksServed;

import org.springframework.stereotype.Component;

@Component
public class MarkDrinksServedHandler implements CommandHandler<MarkDrinksServed> {

  @Override
  public Class<MarkDrinksServed> type() {
    return MarkDrinksServed.class;
  }

  @Override
  public void on(MarkDrinksServed command) {
    info("handling : %s", command);

    command.progress().success();
  }
}
