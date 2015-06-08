package com.vach.cafe.command;

import com.vach.cafe.Command;
import com.vach.cafe.aggregate.tab.OrderedItem;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class PlaceOrder extends Command {

  public List<OrderedItem> items;

  public PlaceOrder(long id, List<OrderedItem> items) {
    super(id);
    this.items = items;
  }

  public List<OrderedItem> getFoodOrders(){
    return items.stream()
        .filter(OrderedItem::isFood)
        .collect(toList());
  }

  public List<OrderedItem> getDrinkOrders() {
    return items.stream()
        .filter(OrderedItem::isDrink)
        .collect(toList());
  }

}
