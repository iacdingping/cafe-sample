package com.vach.cafe.event;

import com.vach.cafe.Event;

import static com.vach.cafe.encoder.TypeRegister.EVENT_DRINKS_SERVED;

public class DrinksServed extends Event {

  public DrinksServed(long id) {
    super(id, EVENT_DRINKS_SERVED);
  }

}
