package com.vach.cafe.aggregate.tab.handler.event;

import com.vach.cafe.EventHandler;
import com.vach.cafe.event.FoodCanceled;

import org.springframework.stereotype.Component;

@Component
public class FoodCanceledHandler implements EventHandler<FoodCanceled> {

  @Override
  public Class<FoodCanceled> type() {
    return FoodCanceled.class;
  }

  @Override
  public void on(FoodCanceled event) {
    info("got event : %s", event);
  }
}
