package com.vach.cafe.server.event;

import com.vach.cafe.server.Event;

import java.util.UUID;

public class FoodCanceled extends Event {

  public FoodCanceled(UUID id) {
    super(id);
  }

}
