package com.vach.cafe.event;

import com.vach.cafe.Event;
import com.vach.cafe.EventHandler;

public class TabOpened extends Event {

  public long id;

  public interface IHandleTabOpened extends EventHandler<TabOpened>{
    void apply(TabOpened event);
  }

  public int tableNumber;
  public String waiter;

  public TabOpened(long id, int tableNumber, String waiter) {
    super(id);
    this.tableNumber = tableNumber;
    this.waiter = waiter;
  }

  

}
