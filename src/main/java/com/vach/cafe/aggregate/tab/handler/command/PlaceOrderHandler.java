package com.vach.cafe.aggregate.tab.handler.command;

import com.vach.cafe.CommandHandler;
import com.vach.cafe.Repository;
import com.vach.cafe.aggregate.tab.Tab;
import com.vach.cafe.command.PlaceOrder;
import com.vach.cafe.exception.TabNotOpen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PlaceOrderHandler implements CommandHandler<PlaceOrder> {
  @Autowired
  Repository<Tab> repo;

  @Override
  public Class<PlaceOrder> type() {
    return PlaceOrder.class;
  }

  @Override
  public void on(PlaceOrder command) {
    info("handling : %s", command);

    Tab tab = repo.get(command.aggregateId());

    try {
      tab.placeOrder(command);
      command.progress().success();
    } catch (TabNotOpen tabNotOpen) {
      command.progress().fail(tabNotOpen);
    }
  }
}
