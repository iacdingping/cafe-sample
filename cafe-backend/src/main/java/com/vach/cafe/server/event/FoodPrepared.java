package com.vach.cafe.server.event;

import com.vach.cafe.server.Event;
import com.vach.cafe.server.aggregate.tab.OrderedItem;

import java.util.List;
import java.util.UUID;

import static java.util.Arrays.asList;

public class FoodPrepared extends Event {

  public List<OrderedItem> items;

  public FoodPrepared(UUID id, List<OrderedItem> items) {
    super(id);
    this.items = items;
  }

  public FoodPrepared(UUID id, OrderedItem... items) {
    super(id);
    this.items = asList(items);
  }
}
