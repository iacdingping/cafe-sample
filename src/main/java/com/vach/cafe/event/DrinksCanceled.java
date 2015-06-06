package com.vach.cafe.event;

import com.vach.cafe.Event;

import static com.vach.cafe.encoder.TypeRegister.EVENT_DRINKS_CANCELED;

public class DrinksCanceled extends Event {

  public DrinksCanceled(long id) {
    super(id, EVENT_DRINKS_CANCELED);
  }

}
