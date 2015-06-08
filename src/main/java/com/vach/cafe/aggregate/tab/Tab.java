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

    handle(tabOpened);

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
      handle(drinksOrdered);
    }

    List<OrderedItem> foodOrders = command.getFoodOrders();
    if (!foodOrders.isEmpty()) {
      FoodOrdered foodOrdered = new FoodOrdered(command.id, foodOrders);
      handle(foodOrdered);
    }

    return events;
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

    DrinksServed event = new DrinksServed(command.id, command.menuNumbers);
    handle(event);

    return asList(event);
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

    return handleEvent(
        new TabClosed(
            command.id,
            command.amountPaid,
            servedItemsValue,
            command.amountPaid - servedItemsValue
        )
    );
  }
  // event handlers

  public void handle(TabOpened event) {
    info("handling %s event", event.getClass().getSimpleName());

    open = true;
    this.id = event.id;
    this.table = event.tableNumber;
    this.waiter = event.waiter;
  }

  public void handle(DrinksOrdered event) {
    info("handling %s event", event.getClass().getSimpleName());

    for (OrderedItem item : event.items) {
      outstandingDrinks.put(item.menuNumber, item);
    }
  }

  public void handle(FoodOrdered event) {
    info("handling %s event", event.getClass().getSimpleName());

    this.outstandingFood.addAll(event.items);

  }

  public void handle(DrinksServed event) {
    event.menuNumbers.stream()
        .map(outstandingDrinks::remove)
        .forEach(item -> servedItemsValue += item.price);
  }

  public void handle(TabClosed event) {
    this.open = false;
  }

  // stuff

  private boolean isOutstandingDrink(Integer menuNumber) {
    return outstandingDrinks.containsKey(menuNumber);
  }
}
