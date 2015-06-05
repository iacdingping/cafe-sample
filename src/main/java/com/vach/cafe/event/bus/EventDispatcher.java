package com.vach.cafe.event.bus;

import com.vach.cafe.Dispatcher;
import com.vach.cafe.Event;
import com.vach.cafe.EventHandler;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.vach.cafe.util.Util.wtf;


/**
 * EventDispatcher does not execute any business logic itself, instead it delegates event to appropriate handler.
 */
public class EventDispatcher implements Dispatcher<Event> {

  private Map<Class, EventHandler> handlers = new HashMap<>();

  @Autowired
  public EventDispatcher(List<EventHandler> handlers) {
    debug("registering handlers");
    for (EventHandler handler : handlers) {
      trace("handler : %s", handler.getClass().getSimpleName());
      this.handlers.put(handler.type(), handler);
    }
  }

  @Override
  @SuppressWarnings("unchecked")
  public void dispatch(Event event) {
    trace("dispatching event : %s", event);

    EventHandler handler = handlers.get(event.getClass());

    if (handler == null) {
      wtf("no handler is registered for type : %s", event.getClass());
    }
    handler.on(event);
  }

  // TODO implement batch event processing
}
