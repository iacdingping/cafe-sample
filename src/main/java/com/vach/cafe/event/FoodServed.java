package com.vach.cafe.event;

import com.vach.cafe.Event;

import static com.vach.cafe.encoder.TypeRegister.EVENT_FOOD_SERVED;

public class FoodServed extends Event{

  public FoodServed(long id) {
    super(id, EVENT_FOOD_SERVED);
  }

}
