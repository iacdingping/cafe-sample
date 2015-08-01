package com.vach.cafe.server.event;

import com.vach.cafe.server.Event;

import java.util.List;

import static java.util.Arrays.asList;

public class FoodPrepared extends Event {

  public List<com.vach.cafe.server.tab.OrderedItem> items;

  public FoodPrepared(long id, List<com.vach.cafe.server.tab.OrderedItem> items) {
    super(id);
    this.items = items;
  }

  public FoodPrepared(long id, com.vach.cafe.server.tab.OrderedItem... items) {
    super(id);
    this.items = asList(items);
  }
}
