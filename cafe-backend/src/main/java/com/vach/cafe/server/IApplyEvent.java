package com.vach.cafe.server;


import com.vach.cafe.util.Util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.List;

import static com.vach.cafe.util.Util.wtf;



/**
 * Event handler interface.
 *
 * NOTE : default implementation will use reflection and rely on convention to
 * invoke appropriate handler method.
 */
public interface IApplyEvent {

  /**
   * Annotation for EventHandler methods.
   *
   * EventHandlers must accept event or List of events, never throw exception,
   * be accessible and return void.
   */
  @Target(ElementType.METHOD)
  @Retention(RetentionPolicy.RUNTIME)
  @interface EventHandler {
  }

  default <E extends Event> List<E> applyEvents(List<E> events) {
    events.forEach(this::applyEvent);
    return events;
  }

  /**
   * Apply single event.
   */
  @SuppressWarnings("unchecked")
  default <E extends Event> E applyEvent(E event) {

    Class currentClass = this.getClass();
    Class eventClass = event.getClass();

    // find method accepting event

    try {
      Method handler = currentClass.getDeclaredMethod("apply", eventClass);

      assert handler.isAnnotationPresent(EventHandler.class);

      handler.invoke(this, event);

    } catch (Exception e) {
      wtf(e, "aggregate does not support events of type : %s", event.getClass());
    }

    return event;
  }

  default List<Class<? extends Event>> getApplicableEventTypes() {
    return Util.getSupportedEventTypes(this.getClass());
  }
}
