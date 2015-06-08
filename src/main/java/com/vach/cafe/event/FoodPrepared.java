package com.vach.cafe.event;

import com.vach.cafe.Event;
import com.vach.cafe.aggregate.tab.OrderedItem;

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
