package com.vach.cafe.server.event;

import com.vach.cafe.server.Event;

import java.util.List;

import static java.util.Arrays.asList;

public class DrinksOrdered extends Event {

  public List<com.vach.cafe.server.tab.OrderedItem> items;

  public DrinksOrdered(long id, List<com.vach.cafe.server.tab.OrderedItem> items) {
    super(id);
    this.items = items;
  }

  public DrinksOrdered(long id, com.vach.cafe.server.tab.OrderedItem... items) {
    super(id);
    this.items = asList(items);
  }
}
