package com.vach.cafe;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

import static com.vach.cafe.util.Util.wtf;

public abstract class Aggregate {

  protected long id;
  protected String type;
  protected int version;

  protected List<Event> changes = new ArrayList<>();
  private HashMap<Class, Consumer> handlers = new HashMap<>();
  private Bus<Event> eventBus;

  // interface

  public Aggregate(String type, Bus<Event> eventBus) {
    this.type = type;
    this.eventBus = eventBus;
  }

  /**
   * Unique id of an aggregate
   */
  public long id() {
    return id;
  }

  /**
   * Type of the aggregate
   */
  public String type() {
    return type;
  }

  /**
   * Current version of aggregate
   */
  public int version() {
    return version;
  }

  /**
   * Unmodifiable view of events applied to this aggregate
   */
  public List<Event> changes() {
    return Collections.unmodifiableList(changes);
  }

  /**
   * Apply events to aggregate
   */
  public void load(List<Event> events) {

    events.forEach(this::apply);
  }

  /**
   * Apply events to aggregate
   */
  public void load(Event... events) {
    for (Event event : events) {
      this.apply(event);
    }
  }

  // environment

  /**
   * Register handler for particular type of Event
   */
  protected <T extends Event> void registerHandler(Class<T> type, Consumer<T> handler) {
    handlers.put(
        type,
        handler
            // add additional logic to handlers
            .andThen((event) -> {
              // add event to changes
              this.changes.add(event);
              // increment the version
              this.version++;
              // emit event to all subscribers
              eventBus.publish(event);
            })
    );
  }

  /**
   * Dispatches the event to appropriate handler method
   */
  public void apply(Event event) {
    Consumer handler = handlers.get(event.getClass());

    if (handler == null) {
      wtf("aggregate does not support events of type : " + event.getClass());
    }

    try {
      handler.accept(event);
    } catch (Exception e) {
      wtf("apply method shall never throw exception", e);
    }

  }
}