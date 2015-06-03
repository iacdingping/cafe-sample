package com.vach.cafe.aggregate.tab;

import com.vach.cafe.Aggregate;
import com.vach.cafe.event.FoodCanceled;
import com.vach.cafe.event.FoodOrdered;
import com.vach.cafe.event.FoodPrepared;
import com.vach.cafe.event.FoodServed;
import com.vach.cafe.event.TabClosed;
import com.vach.cafe.event.TabOpened;

public class Tab extends Aggregate {

  private int tableNumber;
  private String waiter;

  public Tab() {
    super("tab");
    
    registerHandler(TabOpened.class, this::apply);
    registerHandler(TabClosed.class, this::apply);
    registerHandler(FoodOrdered.class, this::apply);
    registerHandler(FoodCanceled.class, this::apply);
    registerHandler(FoodPrepared.class, this::apply);
    registerHandler(FoodServed.class, this::apply);
  }

  private void apply(TabOpened event){
    this.tableNumber = event.tableNumber;
    this.waiter = event.waiter;
  }

  private void apply(TabClosed event){

  }

  private void apply(FoodOrdered event){

  }

  private void apply(FoodCanceled event){

  }

  private void apply(FoodPrepared event){

  }

  private void apply(FoodServed event){

  }

}
