package com.vach.cafe.aggregate.tab.handler.event;

import com.vach.cafe.EventHandler;
import com.vach.cafe.event.DrinksCanceled;

import org.springframework.stereotype.Component;

@Component
public class DrinksCanceledHandler implements EventHandler<DrinksCanceled> {

  @Override
  public Class<DrinksCanceled> type() {
    return DrinksCanceled.class;
  }

  @Override
  public void on(DrinksCanceled event) {
    info("got event : %s", event);
  }
}
