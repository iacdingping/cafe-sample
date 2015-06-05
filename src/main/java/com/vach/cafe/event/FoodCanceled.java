package com.vach.cafe.event;

import com.vach.cafe.Event;

import static com.vach.cafe.encoder.TypeRegister.EVENT_FOOD_CANCELED;

public class FoodCanceled extends Event {

  public FoodCanceled(long id) {
    super(id, EVENT_FOOD_CANCELED);
  }

}
