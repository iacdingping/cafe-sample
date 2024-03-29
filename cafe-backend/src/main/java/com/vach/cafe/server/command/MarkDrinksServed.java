package com.vach.cafe.server.command;

import java.util.List;
import java.util.UUID;

import static java.util.Arrays.asList;

public class MarkDrinksServed extends com.vach.cafe.server.Command {

  public List<Integer> menuNumbers;

  public MarkDrinksServed(UUID id, List<Integer> menuNumbers) {
    super(id);
    this.menuNumbers = menuNumbers;
  }
  public MarkDrinksServed(UUID id, Integer... menuNumbers) {
    super(id);
    this.menuNumbers = asList(menuNumbers);
  }
}
