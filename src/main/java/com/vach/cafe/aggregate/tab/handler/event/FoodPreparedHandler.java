package com.vach.cafe.aggregate.tab.handler.event;

import com.vach.cafe.EventHandler;
import com.vach.cafe.event.FoodPrepared;

import org.springframework.stereotype.Component;

@Component
public class FoodPreparedHandler implements EventHandler<FoodPrepared>{

  @Override
  public Class<FoodPrepared> type() {
    return FoodPrepared.class;
  }

  @Override
  public void on(FoodPrepared event) {
    info("got event : %s", event);
  }
}
