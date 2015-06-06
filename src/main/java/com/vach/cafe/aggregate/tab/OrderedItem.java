package com.vach.cafe.aggregate.tab;

import com.google.common.base.Objects;

public class OrderedItem {
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



  @Override
  public String toString() {
    return String.format("{ %s : %s }", description, price);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;

    OrderedItem that = (OrderedItem) o;

    return Objects.equal(this.menuNumber, that.menuNumber) &&
           Objects.equal(this.price, that.price) &&
           Objects.equal(this.isDrink, that.isDrink) &&
           Objects.equal(this.description, that.description);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(menuNumber, price, isDrink, description);
  }
}
