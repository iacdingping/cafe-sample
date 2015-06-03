package com.vach.cafe.aggregate.tab.handler.event;

import com.vach.cafe.EventHandler;
import com.vach.cafe.event.FoodServed;

import org.springframework.stereotype.Component;

@Component
public class FoodServedHandler implements EventHandler<FoodServed>{

  @Override
  public Class<FoodServed> type() {
    return FoodServed.class;
  }

  @Override
  public void on(FoodServed event) {
    info("got event : %s", event);
  }
}
