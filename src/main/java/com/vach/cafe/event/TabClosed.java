package com.vach.cafe.event;

import com.vach.cafe.Event;
import com.vach.cafe.EventHandler;

public class TabClosed extends Event {

  public interface IHandleTabOpened extends EventHandler<TabClosed> {

    void apply(TabClosed event);
  }

  public TabClosed(long id) {
    super(id);
  }


}
