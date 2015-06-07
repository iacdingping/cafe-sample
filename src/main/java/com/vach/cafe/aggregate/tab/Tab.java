package com.vach.cafe.aggregate.tab;

import com.vach.cafe.Aggregate;
import com.vach.cafe.Event;
import com.vach.cafe.command.OpenTab;
import com.vach.cafe.command.OpenTab.IHandleOpenTab;
import com.vach.cafe.event.TabOpened;
import com.vach.cafe.event.TabOpened.IHandleTabOpened;
import com.vach.cafe.exception.TabIsOpen;

import java.util.List;

import static java.util.Arrays.asList;

public class Tab extends Aggregate implements IHandleOpenTab, IHandleTabOpened {

  boolean isOpen;
  int table;
  String waiter;

  // command handlers

  @Override
  public List<Event> handle(OpenTab command) throws TabIsOpen {

    if(isOpen) throw new TabIsOpen();

    TabOpened tabOpened = new TabOpened(
        command.id,
        command.tableNumber,
        command.waiter
    );

    apply(tabOpened);

    return asList(tabOpened);
  }

  // event handlers

  @Override
  public void apply(TabOpened event) {
    isOpen = true;
    this.id = event.id;
    this.table = event.tableNumber;
    this.waiter = event.waiter;
  }
}
