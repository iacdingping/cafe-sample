package com.vach.cafe.event;

import com.vach.cafe.Event;
import com.vach.cafe.aggregate.tab.OrderedItem;

import java.util.List;

import static com.vach.cafe.encoder.TypeRegister.EVENT_DRINKS_ORDERED;

public class DrinksOrdered extends Event {

  List<OrderedItem> items;

  public DrinksOrdered(long id) {
    super(id, EVENT_DRINKS_ORDERED);
  }

}
