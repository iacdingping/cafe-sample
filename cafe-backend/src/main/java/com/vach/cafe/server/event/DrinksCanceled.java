package com.vach.cafe.server.event;


import com.vach.cafe.server.Event;

import java.util.UUID;

public class DrinksCanceled extends Event {

  public DrinksCanceled(UUID id) {
    super(id);
  }

}
