package com.vach.cafe.server;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.vach.cafe.server.util.Util.cast;
import static com.vach.cafe.server.util.Util.wtf;


/**
 * Event handler interface.
 *
 * NOTE : default implementation will use reflection and rely on convention to
 * invoke appropriate handler method.
 */
public interface IHandleEvent {

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

  default <E extends Event> List<E> handleEvents(List<E> events) {
    events.forEach(this::handleEvent);
    return events;
  }

  /**
   * Apply single event.
   */
  @SuppressWarnings("unchecked")
  default <E extends Event> E handleEvent(E event) {

    Class currentClass = this.getClass();
    Class eventClass = event.getClass();

    // find method accepting event

    try {
      Method handler = currentClass.getDeclaredMethod("handle", eventClass);

      assert handler.isAnnotationPresent(EventHandler.class);

      handler.invoke(this, event);

    } catch (Exception e) {
      wtf(e, "aggregate does not support events of type : %s", event.getClass());
    }

    return event;
  }

  default List<Class<? extends Event>> getSupportedEventTypes() {
    Class currentClass = this.getClass();

    List<Class<? extends Event>> result = new ArrayList<>();

    Arrays.stream(currentClass.getMethods())
        .filter(unfilteredMethod -> unfilteredMethod.isAnnotationPresent(EventHandler.class))
        .map(method -> method.getParameterTypes()[0])
        .forEach(type -> result.add(cast(type)));

    return result;
  }
}
