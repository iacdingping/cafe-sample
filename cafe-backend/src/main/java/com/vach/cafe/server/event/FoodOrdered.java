package com.vach.cafe.server.event;


import com.vach.cafe.server.Event;
import com.vach.cafe.server.aggregate.tab.OrderedItem;

import java.util.List;
import java.util.UUID;

import static java.util.Arrays.asList;

public class FoodOrdered extends Event {

  public List<OrderedItem> items;

  public FoodOrdered(UUID id, List<OrderedItem> items) {
    super(id);
    this.items = items;
  }

  public FoodOrdered(UUID id, OrderedItem... items) {
    super(id);
    this.items = asList(items);
  }
}
