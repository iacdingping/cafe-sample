package com.vach.cafe.command;

import com.vach.cafe.Command;
import com.vach.cafe.aggregate.tab.OrderedItem;

import java.util.List;

import static com.vach.cafe.encoder.TypeRegister.COMMAND_PLACE_ORDER;
import static java.util.stream.Collectors.toList;

public class PlaceOrder extends Command {

  public List<OrderedItem> items;

  public PlaceOrder(long id, List<OrderedItem> items) {
    super(id, COMMAND_PLACE_ORDER);
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

  @Override
  public String toString() {
    return String.format("%s %s", type, items);
  }
}
