package com.vach.cafe;


import com.vach.cafe.util.ICanLog;

import java.util.ArrayList;
import java.util.List;

import static com.vach.cafe.util.Util.wtf;

public abstract class Aggregate implements ICanLog {

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

  // behaviour

//  public <E extends Event> void applyEvents(E... events) {
//    for (E event : events) {
//      this.applyEvent(event);
//    }
//  }

  public <E extends Event> void applyEvents(List<E> events) {
    events.forEach(this::applyEvent);
  }

  public <E extends Event> void applyEvent(E event) {
    try {
      // apply state change
      ((EventHandler<E>) this).apply(event);

      // add the change
      changes.add(event);

      // increment state version
      this.version++;

    } catch (ClassCastException e) {
      wtf("aggregate does not support events of type : " + event.getClass());
    } catch (Exception e) {
      wtf("apply method shall never throw exception", e);
    }
  }

  public <C extends Command> List<Event> handleCommand(C command) throws Exception {
    try {
      return ((CommandHandler<C>) this).handle(command);
    } catch (ClassCastException e) {
      return wtf("aggregate does handle commands of type : " + command.getClass());
    }
  }
}