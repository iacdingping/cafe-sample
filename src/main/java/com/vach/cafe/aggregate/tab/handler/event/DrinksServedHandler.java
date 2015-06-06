package com.vach.cafe.aggregate.tab.handler.event;

import com.vach.cafe.EventHandler;
import com.vach.cafe.event.DrinksServed;

import org.springframework.stereotype.Component;

@Component
public class DrinksServedHandler implements EventHandler<DrinksServed> {

  @Override
  public Class<DrinksServed> type() {
    return DrinksServed.class;
  }

  @Override
  public void on(DrinksServed event) {
    info("got event : %s", event);
  }
}
