package com.vach.cafe.aggregate.tab;

import com.vach.cafe.Aggregate;
import com.vach.cafe.Event;
import com.vach.cafe.command.OpenTab;
import com.vach.cafe.command.PlaceOrder;
import com.vach.cafe.event.DrinksOrdered;
import com.vach.cafe.event.FoodOrdered;
import com.vach.cafe.event.TabOpened;
import com.vach.cafe.exception.TabIsOpen;
import com.vach.cafe.exception.TabNotOpen;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class Tab extends Aggregate {

  boolean open;
  int table;
  String waiter;

  private List<OrderedItem> outstandingDrinks = new ArrayList<>();
  private List<OrderedItem> outstandingFood = new ArrayList<>();
  private List<OrderedItem> preparedFood = new ArrayList<>();

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
      apply(drinksOrdered);
      events.add(drinksOrdered);
    }

    List<OrderedItem> foodOrders = command.getDrinkOrders();
    if (!foodOrders.isEmpty()) {
      FoodOrdered foodOrdered = new FoodOrdered(command.id, foodOrders);
      apply(foodOrdered);
      events.add(foodOrdered);
    }

    return events;
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

    this.outstandingDrinks.addAll(event.items);
  }

  public void apply(FoodOrdered event) {
    info("applying %s event", event.getClass().getSimpleName());

    this.outstandingFood.addAll(event.items);

  }
}
