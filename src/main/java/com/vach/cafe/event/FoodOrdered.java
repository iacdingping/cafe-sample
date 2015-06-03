package com.vach.cafe.event;

import com.vach.cafe.Event;

import static com.vach.cafe.encoder.TypeRegister.EVENT_FOOD_ORDERED;

public class FoodOrdered extends Event{

  public FoodOrdered(long id) {
    super(id, EVENT_FOOD_ORDERED);
  }

}
