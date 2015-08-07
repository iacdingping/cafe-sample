package com.vach.cafe.server.subscriber.cheaf;

import com.vach.cafe.server.ValueType;

/**
 * Single item of chefs todoList.
 */
@ValueType
public class TodoListItem {

  public final int menuNumber;
  public final String description;

  TodoListItem(int menuNumber, String description) {
    this.menuNumber = menuNumber;
    this.description = description;
  }
}