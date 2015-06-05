package com.vach.cafe.aggregate.tab.handler.command;

import com.vach.cafe.CommandHandler;
import com.vach.cafe.command.AmendOrder;

import org.springframework.stereotype.Component;

@Component
public class AmendOrderHandler implements CommandHandler<AmendOrder> {

  @Override
  public Class<AmendOrder> type() {
    return AmendOrder.class;
  }

  @Override
  public void on(AmendOrder command) {
    info("handling : %s", command);

    command.progress().success();
  }
}
