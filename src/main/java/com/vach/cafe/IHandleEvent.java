package com.vach.cafe;

import java.lang.reflect.Method;
import java.util.List;

import static com.vach.cafe.util.Util.wtf;
import static java.util.Arrays.asList;

/**
 * Event handler interface.
 *
 * NOTE : default implementation will use reflection and rely on convention to
 * invoke appropriate handler method.
 */
public interface IHandleEvent {

  default <E extends Event> List<E> handleEvent(List<E> events) {
    events.forEach(this::handleEvent);
    return events;
  }

  /**
   * Apply single event.
   */
  @SuppressWarnings("unchecked")
  default <E extends Event> List<E> handleEvent(E event) {

    Class currentClass = this.getClass();
    Class eventClass = event.getClass();

    // find method accepting event

    try {
      Method handler = currentClass.getDeclaredMethod("handle", eventClass);
      handler.invoke(this, event);

    } catch (Exception e) {
      wtf(e, "aggregate does not support events of type : %s", event.getClass());
    }

    return asList(event);
  }
}
