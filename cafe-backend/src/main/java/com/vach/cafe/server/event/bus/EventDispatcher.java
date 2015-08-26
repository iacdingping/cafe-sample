package com.vach.cafe.server.event.bus;

import com.vach.cafe.server.bus.Dispatcher;
import com.vach.cafe.server.Event;

/**
 * EventDispatcher does not execute any business logic itself, instead it delegates event to appropriate handler.
 */
public class EventDispatcher implements Dispatcher<Event> {

  @Override
  public void dispatch(com.vach.cafe.server.Event event) {

  }

  // TODO implement batch event processing
}
