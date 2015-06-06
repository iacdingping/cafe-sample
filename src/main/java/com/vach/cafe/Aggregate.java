package com.vach.cafe;


import com.vach.cafe.util.ICanLog;

import java.util.ArrayList;
import java.util.List;

import static com.vach.cafe.util.Util.wtf;

public abstract class Aggregate implements ICanLog{

  protected long id;
  protected int version;
  protected List<Event> changes = new ArrayList<>();

  // interface

  /**
   * Unique id of an aggregate
   */
  public long id() {
    return id;
  }

  /**
   * Current version of aggregate
   */
  public int version() {
    return version;
  }


  /**
   * Apply events to aggregate
   */
  public  void load(List<Event> events) {
    for (Event event : events) {
      this.applyEvent(event, false);
    }
  }

  /**
   * Apply events to aggregate
   */
  public void applyEvents(Event... events) {
    for (Event event : events) {
      this.applyEvent(event, false);
    }
  }

  // environment

  /**
   * Dispatches the event to appropriate handler method
   */
  public void applyEvent(Event event) {
    applyEvent(event, true);
  }

  /**
   * Dispatches the event to appropriate handler method
   */
  private <E extends Event> void applyEvent(E event, boolean isNew) {

    try {
      EventHandler<E> handler = (EventHandler<E>) this;

      try {
        // apply state change
        handler.apply(event);

        // add the change
        changes.add(event);

        // increment state version
        this.version++;

      } catch (Exception e) {
        wtf("apply method shall never throw exception", e);
      }

    }catch (ClassCastException e){
      wtf("aggregate does not support events of type : " + event.getClass());
    }

  }
}