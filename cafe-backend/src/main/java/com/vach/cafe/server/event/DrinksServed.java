package com.vach.cafe.server.event;

import com.vach.cafe.server.Event;

import java.util.List;
import java.util.UUID;

import static java.util.Arrays.asList;

public class DrinksServed extends Event {
  public List<Integer> menuNumbers;

  public DrinksServed(UUID id, List<Integer> menuNumbers) {
    super(id);
    this.menuNumbers = menuNumbers;
  }
  public DrinksServed(UUID id, Integer... menuNumbers) {
    super(id);
    this.menuNumbers = asList(menuNumbers);
  }

}
