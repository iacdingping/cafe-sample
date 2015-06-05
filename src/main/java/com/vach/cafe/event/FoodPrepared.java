package com.vach.cafe.event;

import com.vach.cafe.Event;

import static com.vach.cafe.encoder.TypeRegister.EVENT_FOOD_PREPARED;

public class FoodPrepared extends Event {

  public FoodPrepared(long id) {
    super(id, EVENT_FOOD_PREPARED);
  }

}
