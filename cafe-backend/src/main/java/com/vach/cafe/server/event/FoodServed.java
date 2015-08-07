package com.vach.cafe.server.event;

import com.vach.cafe.server.Event;

import java.util.UUID;

public class FoodServed extends Event {

  public FoodServed(UUID id) {
    super(id);
  }

}
