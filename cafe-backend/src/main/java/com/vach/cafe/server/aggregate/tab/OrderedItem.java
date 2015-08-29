package com.vach.cafe.server.aggregate.tab;

import com.vach.cafe.server.ValueType;
import com.vach.cafe.util.ObjectSupport;

@ValueType
public class OrderedItem extends ObjectSupport {

  private final int menuNumber;
  private final double price;
  private final boolean isDrink;
  private final String description;

  public OrderedItem(int menuNumber, double price, boolean isDrink, String description) {
    this.description = description;
    this.isDrink = isDrink;
    this.menuNumber = menuNumber;
    this.price = price;
  }

  public boolean isDrink() {
    return isDrink;
  }

  public boolean isFood() {
    return !isDrink;
  }

  public int menuNumber() {
    return menuNumber;
  }

  public double price(){
    return price;
  }

  public String description(){
    return description;
  }
}
