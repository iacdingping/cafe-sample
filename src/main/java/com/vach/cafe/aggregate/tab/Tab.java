package com.vach.cafe.aggregate.tab;

import com.vach.cafe.Aggregate;
import com.vach.cafe.Event;
import com.vach.cafe.command.CloseTab;
import com.vach.cafe.command.MarkDrinksServed;
import com.vach.cafe.command.OpenTab;
import com.vach.cafe.command.PlaceOrder;
import com.vach.cafe.event.DrinksOrdered;
import com.vach.cafe.event.DrinksServed;
import com.vach.cafe.event.FoodOrdered;
import com.vach.cafe.event.TabClosed;
import com.vach.cafe.event.TabOpened;
import com.vach.cafe.exception.DrinksNotOutstanding;
import com.vach.cafe.exception.MustPayEnough;
import com.vach.cafe.exception.TabHasUnservedItems;
import com.vach.cafe.exception.TabIsOpen;
import com.vach.cafe.exception.TabNotOpen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;

public class Tab extends Aggregate {

  boolean open;
  int table;
  String waiter;

  private Map<Integer, OrderedItem> outstandingDrinks = new HashMap<>();
  private List<OrderedItem> outstandingFood = new ArrayList<>();
  private List<OrderedItem> preparedFood = new ArrayList<>();

  private double servedItemsValue = 0;

  // command handlers

  public List<Event> handle(OpenTab command) throws TabIsOpen {
    info("handle %s command", command.getClass().getSimpleName());

    if (open) {
      throw new TabIsOpen();
    }

    TabOpened tabOpened = new TabOpened(
        command.id,
        command.tableNumber,
        command.waiter
    );

    apply(tabOpened);

    return asList(tabOpened);
  }

  public List<Event> handle(PlaceOrder command) throws TabNotOpen {
    info("handle %s command", command.getClass().getSimpleName());

    if (!open) {
      throw new TabNotOpen();
    }

    List<Event> events = new ArrayList<>();

    List<OrderedItem> drinkOrders = command.getDrinkOrders();
    if (!drinkOrders.isEmpty()) {
      DrinksOrdered drinksOrdered = new DrinksOrdered(command.id, drinkOrders);
      events.add(drinksOrdered);
    }

    List<OrderedItem> foodOrders = command.getFoodOrders();
    if (!foodOrders.isEmpty()) {
      FoodOrdered foodOrdered = new FoodOrdered(command.id, foodOrders);
      events.add(foodOrdered);
    }

    return applyEvents(events);
  }

  public List<Event> handle(MarkDrinksServed command) throws TabNotOpen, DrinksNotOutstanding {
    info("handle %s command", command.getClass().getSimpleName());

    if (!open) {
      throw new TabNotOpen();
    }

    for (Integer menuNumber : command.menuNumbers) {
      if (!isOutstandingDrink(menuNumber)) {
        throw new DrinksNotOutstanding();
      }
    }

    return applyEvent(new DrinksServed(command.id, command.menuNumbers));
  }

  public List<Event> handle(CloseTab command) throws TabNotOpen, TabHasUnservedItems, MustPayEnough {
    info("handle %s command", command.getClass().getSimpleName());

    if (!open) {
      throw new TabNotOpen();
    }

    if (!outstandingDrinks.isEmpty() || !outstandingFood.isEmpty()) {
      throw new TabHasUnservedItems();
    }

    if (command.amountPaid < servedItemsValue) {
      throw new MustPayEnough();
    }

    return applyEvent(
        new TabClosed(
            command.id,
            command.amountPaid,
            servedItemsValue,
            command.amountPaid - servedItemsValue
        )
    );
  }
  // event handlers

  public void apply(TabOpened event) {
    info("applying %s event", event.getClass().getSimpleName());

    open = true;
    this.id = event.id;
    this.table = event.tableNumber;
    this.waiter = event.waiter;
  }

  public void apply(DrinksOrdered event) {
    info("applying %s event", event.getClass().getSimpleName());

    for (OrderedItem item : event.items) {
      outstandingDrinks.put(item.menuNumber, item);
    }
  }

  public void apply(FoodOrdered event) {
    info("applying %s event", event.getClass().getSimpleName());

    this.outstandingFood.addAll(event.items);

  }

  public void apply(DrinksServed event) {
    event.menuNumbers.stream()
        .map(outstandingDrinks::remove)
        .forEach(item -> servedItemsValue += item.price);
  }

  public void apply(TabClosed event) {
    this.open = false;
  }

  // stuff

  private boolean isOutstandingDrink(Integer menuNumber) {
    return outstandingDrinks.containsKey(menuNumber);
  }
}
