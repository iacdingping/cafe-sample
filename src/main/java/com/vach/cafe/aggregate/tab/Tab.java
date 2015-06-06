package com.vach.cafe.aggregate.tab;

import com.vach.cafe.Aggregate;
import com.vach.cafe.Bus;
import com.vach.cafe.Event;
import com.vach.cafe.command.OpenTab;
import com.vach.cafe.command.PlaceOrder;
import com.vach.cafe.event.DrinksCanceled;
import com.vach.cafe.event.DrinksOrdered;
import com.vach.cafe.event.DrinksServed;
import com.vach.cafe.event.FoodCanceled;
import com.vach.cafe.event.FoodOrdered;
import com.vach.cafe.event.FoodPrepared;
import com.vach.cafe.event.FoodServed;
import com.vach.cafe.event.TabClosed;
import com.vach.cafe.event.TabOpened;
import com.vach.cafe.exception.TabIsOpen;
import com.vach.cafe.exception.TabNotOpen;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class Tab extends Aggregate {

  private boolean isOpen;

  private int tableNumber;
  private String waiter;

  private List<OrderedItem> orderedFood = new ArrayList<>();
  private List<OrderedItem> orderedDrinks = new ArrayList<>();

  private List<OrderedItem> servedItems = new ArrayList<>();



  @Autowired
  public Tab(Bus<Event> eventBus) {
    super("tab", eventBus);

    registerHandler(TabOpened.class, this::apply);
    registerHandler(TabClosed.class, this::apply);
    registerHandler(FoodOrdered.class, this::apply);
    registerHandler(FoodCanceled.class, this::apply);
    registerHandler(FoodPrepared.class, this::apply);
    registerHandler(FoodServed.class, this::apply);
    registerHandler(DrinksOrdered.class, this::apply);
    registerHandler(DrinksCanceled.class, this::apply);
    registerHandler(DrinksServed.class, this::apply);
  }

  // event applying methods (no business logic here, only state change)

  private void apply(TabOpened event) {
    this.isOpen = true;
    this.id = event.aggregateId();
    this.tableNumber = event.tableNumber;
    this.waiter = event.waiter;
  }

  private void apply(TabClosed event) {

  }

  private void apply(FoodOrdered event) {
    this.orderedFood.addAll(event.orders);
  }

  private void apply(FoodCanceled event) {

  }

  private void apply(FoodPrepared event) {

  }

  private void apply(FoodServed event) {

  }

  private void apply(DrinksOrdered event) {
    this.orderedDrinks.addAll(event.orders);
  }

  private void apply(DrinksCanceled event) {

  }

  private void apply(DrinksServed event) {

  }

  // interface methods (only business logic here, no state change)

  public void open(OpenTab command) throws TabIsOpen {

    // verify command is valid

    if(isOpen){
      throw new TabIsOpen();
    }

    // apply state change

    super.apply(
        new TabOpened(
            command.aggregateId(),
            command.tableNumber,
            command.waiter
        )
    );

  }

  public void placeOrder(PlaceOrder command) throws TabNotOpen {
    // verify command is valid

    if(!isOpen) throw new TabNotOpen();

    // apply state change

    List<OrderedItem> drinkOrders = command.getDrinkOrders();

    if(!drinkOrders.isEmpty()){
      super.apply(
          new DrinksOrdered(command.aggregateId(), drinkOrders)
      );
    }

    List<OrderedItem> foodOrders = command.getFoodOrders();

    if(!foodOrders.isEmpty()){
      super.apply(
          new FoodOrdered(command.aggregateId(), foodOrders)
      );
    }
  }
}
