package com.vach.cafe.event;

import com.vach.cafe.Event;
import com.vach.cafe.aggregate.tab.OrderedItem;

import java.util.List;

import static java.util.Arrays.asList;

public class FoodOrdered extends Event {

  public List<OrderedItem> items;

  public FoodOrdered(long id, List<OrderedItem> items) {
    super(id);
    this.items = items;
  }

  public FoodOrdered(long id, OrderedItem... items) {
    super(id);
    this.items = asList(items);
  }
}
