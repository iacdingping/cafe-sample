package com.vach.cafe.event.bus;

import com.vach.cafe.Dispatcher;
import com.vach.cafe.Event;

/**
 * EventDispatcher does not execute any business logic itself, instead it delegates event to appropriate handler.
 */
public class EventDispatcher implements Dispatcher<Event> {

  @Override
  public void dispatch(Event event) {

  }

  // TODO implement batch event processing
}
