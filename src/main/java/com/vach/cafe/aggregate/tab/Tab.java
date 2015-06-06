package com.vach.cafe.aggregate.tab;

import com.vach.cafe.Aggregate;
import com.vach.cafe.Event;
import com.vach.cafe.command.OpenTab;
import com.vach.cafe.command.OpenTab.IHandleOpenTab;
import com.vach.cafe.event.TabOpened;
import com.vach.cafe.event.TabOpened.IHandleTabOpened;
import com.vach.cafe.exception.TabIsOpen;

import java.util.List;

public class Tab extends Aggregate implements IHandleOpenTab, IHandleTabOpened {

  // command handlers

  @Override
  public List<Event> handle(OpenTab command) throws TabIsOpen {
    return null;
  }

  // event handlers

  @Override
  public void apply(TabOpened event) {

  }
}
