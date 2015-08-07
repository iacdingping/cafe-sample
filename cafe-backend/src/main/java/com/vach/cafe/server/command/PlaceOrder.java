package com.vach.cafe.server.command;

import com.vach.cafe.server.Command;
import com.vach.cafe.server.aggregate.tab.OrderedItem;

import java.util.List;
import java.util.UUID;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

public class PlaceOrder extends Command {

  public List<OrderedItem> items;

  public PlaceOrder(UUID id, List<OrderedItem> items) {
    super(id);
    this.items = items;
  }
  public PlaceOrder(UUID id, OrderedItem... items) {
    super(id);
    this.items = asList(items);
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
