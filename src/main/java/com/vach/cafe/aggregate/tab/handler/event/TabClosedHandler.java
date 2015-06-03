package com.vach.cafe.aggregate.tab.handler.event;

import com.vach.cafe.EventHandler;
import com.vach.cafe.event.TabClosed;

import org.springframework.stereotype.Component;

@Component
public class TabClosedHandler implements EventHandler<TabClosed>{

  @Override
  public Class<TabClosed> type() {
    return TabClosed.class;
  }

  @Override
  public void on(TabClosed event) {
    info("got event : %s", event);
  }
}
