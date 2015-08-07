package com.vach.cafe.server.subscriber.cheaf;

import com.vach.cafe.server.IHandleEvent;
import com.vach.cafe.server.aggregate.tab.OrderedItem;
import com.vach.cafe.server.event.FoodOrdered;
import com.vach.cafe.server.event.FoodPrepared;
import com.vach.cafe.server.util.ICanLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;


public class ChefTodoList implements IHandleEvent, ICanLog {

  private final Map<UUID, TodoListGroup> todoList = new HashMap<>();

  public List<TodoListGroup> getTodoList() {
    return todoList.values().stream()
        .map(TodoListGroup::copy)
        .collect(Collectors.toList());
  }

  @EventHandler
  public void handle(FoodOrdered event) {
    UUID tabId = event.id;

    // get the todoList for the tab
    TodoListGroup group = todoList.get(tabId);

    // create if none
    if (group == null) {
      group = new TodoListGroup(tabId, new ArrayList<>());
      todoList.put(tabId, group);
    }

    // add orders to the list
    for (OrderedItem item : event.items) {
      TodoListItem todoListItem = new TodoListItem(item.menuNumber(), item.description());
      group.items.put(todoListItem.menuNumber, todoListItem);
    }
  }

  @EventHandler
  public void handle(FoodPrepared event) {
    UUID tabId = event.id;

    // get the todoList for the tab
    TodoListGroup group = todoList.get(tabId);

    // remove prepared from todoList
    for (OrderedItem item : event.items) {
      group.items.remove(item.menuNumber());
    }
  }
}

