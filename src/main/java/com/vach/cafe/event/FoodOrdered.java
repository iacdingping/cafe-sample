package com.vach.cafe.event;

import com.google.common.base.Objects;

import com.vach.cafe.Event;
import com.vach.cafe.aggregate.tab.OrderedItem;

import java.util.List;

import static com.vach.cafe.encoder.TypeRegister.EVENT_FOOD_ORDERED;

public class FoodOrdered extends Event {

  public List<OrderedItem> orders;

  public FoodOrdered(long id, List<OrderedItem> orders) {
    super(id, EVENT_FOOD_ORDERED);
    this.orders = orders;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    FoodOrdered that = (FoodOrdered) o;

    return Objects.equal(this.aggregateId, that.aggregateId) &&
           Objects.equal(this.type, that.type) &&
           Objects.equal(this.orders, that.orders);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(aggregateId, type, orders);
  }

  @Override
  public String toString() {
    return String.format("%s %s", type, orders);
  }

}
