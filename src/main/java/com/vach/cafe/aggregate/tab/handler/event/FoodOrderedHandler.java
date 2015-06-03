package com.vach.cafe.aggregate.tab.handler.event;

import com.vach.cafe.EventHandler;
import com.vach.cafe.event.FoodOrdered;

import org.springframework.stereotype.Component;

@Component
public class FoodOrderedHandler implements EventHandler<FoodOrdered>{

  @Override
  public Class<FoodOrdered> type() {
    return FoodOrdered.class;
  }

  @Override
  public void on(FoodOrdered event) {
    info("got event : %s", event);
  }
}
