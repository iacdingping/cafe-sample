package com.vach.cafe.command;

import com.vach.cafe.Command;
import com.vach.cafe.CommandHandler;
import com.vach.cafe.Event;
import com.vach.cafe.aggregate.tab.OrderedItem;
import com.vach.cafe.exception.TabNotOpen;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class PlaceOrder extends Command {

  public interface IHandlePlaceOrder extends CommandHandler<PlaceOrder> {
    List<Event> handle(PlaceOrder command) throws TabNotOpen;
  }

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
