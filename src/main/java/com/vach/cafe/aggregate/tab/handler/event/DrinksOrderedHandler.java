package com.vach.cafe.aggregate.tab.handler.event;

import com.vach.cafe.EventHandler;
import com.vach.cafe.event.DrinksOrdered;

import org.springframework.stereotype.Component;

@Component
public class DrinksOrderedHandler implements EventHandler<DrinksOrdered> {

  @Override
  public Class<DrinksOrdered> type() {
    return DrinksOrdered.class;
  }

  @Override
  public void on(DrinksOrdered event) {
    info("got event : %s", event);
  }
}
