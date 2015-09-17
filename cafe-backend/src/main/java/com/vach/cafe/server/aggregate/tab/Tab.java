package com.vach.cafe.server.aggregate.tab;

import com.vach.cafe.server.Aggregate;
import com.vach.cafe.server.Event;
import com.vach.cafe.server.command.CloseTab;
import com.vach.cafe.server.command.MarkDrinksServed;
import com.vach.cafe.server.command.OpenTab;
import com.vach.cafe.server.command.PlaceOrder;
import com.vach.cafe.server.event.DrinksOrdered;
import com.vach.cafe.server.event.DrinksServed;
import com.vach.cafe.server.event.FoodOrdered;
import com.vach.cafe.server.event.TabClosed;
import com.vach.cafe.server.event.TabOpened;
import com.vach.cafe.server.exception.DrinksNotOutstanding;
import com.vach.cafe.server.exception.MustPayEnough;
import com.vach.cafe.server.exception.TabHasUnservedItems;
import com.vach.cafe.server.exception.TabIsOpen;
import com.vach.cafe.server.exception.TabNotOpen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import lombok.NonNull;

import static java.util.Arrays.asList;

public class Tab extends Aggregate {

  boolean open;
  int table;
  String waiter;

  private Map<Integer, OrderedItem> outstandingDrinks = new HashMap<>();
  private List<OrderedItem> outstandingFood = new ArrayList<>();
  private List<OrderedItem> preparedFood = new ArrayList<>();

  private double servedItemsValue = 0;

  public Tab(@NonNull UUID id) {
    this.id = id;
  }

  // command handlers

  @CommandHandler
  public List<Event> handle(OpenTab command) throws TabIsOpen {
    info("handle %s command", command.getClass().getSimpleName());

    // validate

    if (open) {
      throw new TabIsOpen();
    }

    // apply

    return asList(
        applyEvent(
            new TabOpened(
                command.id,
                command.tableNumber,
                command.waiter
            )
        )
    );
  }

  @CommandHandler
  public List<Event> handle(PlaceOrder command) throws TabNotOpen {
    info("handle %s command", command.getClass().getSimpleName());

    // validate

    if (!open) {
      throw new TabNotOpen();
    }

    // apply

    List<Event> events = new ArrayList<>();

    List<OrderedItem> drinkOrders = command.getDrinkOrders();
    if (!drinkOrders.isEmpty()) {
      events.add(
          applyEvent(
              new DrinksOrdered(command.id, drinkOrders)
          )
      );
    }

    List<OrderedItem> foodOrders = command.getFoodOrders();
    if (!foodOrders.isEmpty()) {
      events.add(
          applyEvent(
              new FoodOrdered(command.id, foodOrders)
          )
      );
    }

    return events;
  }

  @CommandHandler
  public List<Event> handle(MarkDrinksServed command) throws TabNotOpen, DrinksNotOutstanding {
    info("handle %s command", command.getClass().getSimpleName());

    // validate

    if (!open) {
      throw new TabNotOpen();
    }

    for (Integer menuNumber : command.menuNumbers) {
      if (!isOutstandingDrink(menuNumber)) {
        throw new DrinksNotOutstanding();
      }
    }

    // apply

    return asList(applyEvent(
        new DrinksServed(command.id, command.menuNumbers)
    ));
  }

  @CommandHandler
  public List<Event> handle(CloseTab command)
      throws TabNotOpen, TabHasUnservedItems, MustPayEnough {
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

    return asList(applyEvent(
        new TabClosed(
            command.id,
            command.amountPaid,
            servedItemsValue,
            command.amountPaid - servedItemsValue
        )
    ));
  }

  // event handlers

  @EventHandler
  public void apply(TabOpened event) {
    info("handle %s event", event.getClass().getSimpleName());

    open = true;
    this.id = event.id;
    this.table = event.tableNumber;
    this.waiter = event.waiter;
  }

  @EventHandler
  public void apply(DrinksOrdered event) {
    info("handle %s event", event.getClass().getSimpleName());

    for (OrderedItem item : event.items) {
      outstandingDrinks.put(item.menuNumber(), item);
    }
  }

  @EventHandler
  public void apply(FoodOrdered event) {
    info("handle %s event", event.getClass().getSimpleName());

    this.outstandingFood.addAll(event.items);
  }

  @EventHandler
  public void apply(DrinksServed event) {
    event.menuNumbers.stream()
        .map(outstandingDrinks::remove)
        .forEach(item -> servedItemsValue += item.price());
  }

  @EventHandler
  public void apply(TabClosed event) {
    this.open = false;
  }

  // stuff

  private boolean isOutstandingDrink(Integer menuNumber) {
    return outstandingDrinks.containsKey(menuNumber);
  }
}
