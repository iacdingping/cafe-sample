package com.vach.cafe.server.subscriber.cheaf;

import com.vach.cafe.server.ValueType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Grouped orders of single Tab.
 */
@ValueType
public class TodoListGroup {

  public final UUID tabID;
  public final Map<Integer, TodoListItem> items;

  TodoListGroup(UUID tabID, List<TodoListItem> items) {
    this.tabID = tabID;
    this.items = new HashMap<>();
    for (TodoListItem item : items) {
      this.items.put(item.menuNumber, item);
    }
  }

  TodoListGroup copy() {
    return new TodoListGroup(tabID, new ArrayList<>(items.values()));
  }
}