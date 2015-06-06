package com.vach.cafe.test;

import com.vach.cafe.Dispatcher;
import com.vach.cafe.Event;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * TestEventDispatcher will collect all events and store them,
 * instead of dispatching to event handlers for unit testing purposes.
 */
@Component
public class TestEventDispatcher implements Dispatcher<Event>{
  private List<Event> events = new ArrayList<>();

  @Override
  public void dispatch(Event event) {
    debug("caught event %s", event);
    this.events.add(event);
  }

  public Event[] events(){
    return events.toArray(new Event[events.size()]);
  }
}
