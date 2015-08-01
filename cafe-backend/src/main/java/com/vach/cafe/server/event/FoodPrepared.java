package com.vach.cafe.server.event;

import com.vach.cafe.server.Event;
import com.vach.cafe.server.aggregate.tab.OrderedItem;

import java.util.List;

import static java.util.Arrays.asList;

public class FoodPrepared extends Event {

  public List<OrderedItem> items;

  public FoodPrepared(long id, List<OrderedItem> items) {
    super(id);
    this.items = items;
  }

  public FoodPrepared(long id, OrderedItem... items) {
    super(id);
    this.items = asList(items);
  }
}
