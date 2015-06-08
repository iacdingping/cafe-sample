package com.vach.cafe.event;

import com.vach.cafe.Event;
import com.vach.cafe.EventHandler;
import com.vach.cafe.aggregate.tab.OrderedItem;

import java.util.List;

public class DrinksOrdered extends Event {


  public interface IHandleDrinksOrdered extends EventHandler<DrinksOrdered> {
    void apply(DrinksOrdered event);
  }

  public List<OrderedItem> items;

  public DrinksOrdered(long id, List<OrderedItem> items) {
    super(id);
    this.items = items;
  }
}
