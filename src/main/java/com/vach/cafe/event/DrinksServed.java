package com.vach.cafe.event;

import com.vach.cafe.Event;

import java.util.List;

import static java.util.Arrays.asList;

public class DrinksServed extends Event {
  public List<Integer> menuNumbers;

  public DrinksServed(long id, List<Integer> menuNumbers) {
    super(id);
    this.menuNumbers = menuNumbers;
  }
  public DrinksServed(long id, Integer... menuNumbers) {
    super(id);
    this.menuNumbers = asList(menuNumbers);
  }

}
