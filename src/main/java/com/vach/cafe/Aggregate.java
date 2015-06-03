package com.vach.cafe;


import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

import static com.vach.cafe.util.Util.wtf;

public abstract class Aggregate {

  protected long id;
  protected String type;
  protected int version;
  protected List<Event> changes;
  private HashMap<Class, Consumer> handlers = new HashMap<>();

  // interface

  public Aggregate(String type) {
    this.type = type;
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
            .andThen((e) -> {
              // add event to changes
              this.changes.add(e);
              // increment the version
              this.version++;
            })
    );
  }

  /**
   * Dispatches the event to appropriate handler method
   */
  public void apply(Event event) {
    Consumer handler = handlers.get(event.getClass());

    if (handler == null) {
      wtf("no handler registered for : " + event.getClass());
    }

    try {
      handler.accept(event);
    } catch (Exception e) {
      wtf("apply method shall never throw exception", e);
    }

  }
}