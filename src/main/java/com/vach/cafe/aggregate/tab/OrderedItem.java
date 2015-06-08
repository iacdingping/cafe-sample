package com.vach.cafe.aggregate.tab;

import com.vach.cafe.util.ObjectSupport;

public class OrderedItem extends ObjectSupport{
  public int menuNumber;
  public double price;
  public boolean isDrink;
  public String description;

  public OrderedItem(int menuNumber, double price, boolean isDrink, String description) {
    this.description = description;
    this.isDrink = isDrink;
    this.menuNumber = menuNumber;
    this.price = price;
  }

  public boolean isDrink(){
    return isDrink;
  }

  public boolean isFood(){
    return !isDrink;
  }

  public int menuNumber() {
    return menuNumber;
  }
}
