package com.vach.cafe.command;

import com.vach.cafe.Command;

import java.util.List;

import static java.util.Arrays.asList;

public class MarkDrinksServed extends Command {

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
