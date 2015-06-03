package com.vach.cafe.aggregate.tab.handler.command;

import com.vach.cafe.CommandHandler;
import com.vach.cafe.command.PlaceOrder;

import org.springframework.stereotype.Component;

@Component
public class PlaceOrderHandler implements CommandHandler<PlaceOrder> {

  @Override
  public Class<PlaceOrder> type() {
    return PlaceOrder.class;
  }

  @Override
  public void on(PlaceOrder command) {
    info("handling : %s", command);
  }
}
