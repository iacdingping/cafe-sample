package com.vach.cafe.event;

import com.vach.cafe.Event;
import com.vach.cafe.EventHandler;
import com.vach.cafe.aggregate.tab.OrderedItem;

import java.util.List;

public class FoodOrdered extends Event {

  public interface IHandleFoodOrdered extends EventHandler<FoodOrdered> {
    void apply(FoodOrdered event);
  }

  public List<OrderedItem> items;

  public FoodOrdered(long id, List<OrderedItem> items) {
    super(id);
    this.items = items;
  }
}
