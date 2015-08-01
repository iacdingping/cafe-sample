package com.vach.cafe.server.command;

import java.util.List;

import static java.util.Arrays.asList;

public class MarkDrinksServed extends com.vach.cafe.server.Command {

  public List<Integer> menuNumbers;

  public MarkDrinksServed(long id, List<Integer> menuNumbers) {
    super(id);
    this.menuNumbers = menuNumbers;
  }
  public MarkDrinksServed(long id, Integer... menuNumbers) {
    super(id);
    this.menuNumbers = asList(menuNumbers);
  }
}
