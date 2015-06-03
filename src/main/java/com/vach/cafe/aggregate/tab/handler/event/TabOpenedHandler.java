package com.vach.cafe.aggregate.tab.handler.event;

import com.vach.cafe.EventHandler;
import com.vach.cafe.event.TabOpened;

import org.springframework.stereotype.Component;

@Component
public class TabOpenedHandler implements EventHandler<TabOpened>{

  @Override
  public Class<TabOpened> type() {
    return TabOpened.class;
  }

  @Override
  public void on(TabOpened event) {
    info("got event : %s", event);
  }
}
