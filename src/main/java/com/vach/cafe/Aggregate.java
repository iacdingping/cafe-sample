package com.vach.cafe;


import com.vach.cafe.exception.BaseException;
import com.vach.cafe.util.ICanLog;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static com.vach.cafe.util.Util.wtf;
import static java.util.Arrays.asList;

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

  public <E extends Event> List<E> applyEvents(List<E> events) {
    events.forEach(this::applyEvent);
    return events;
  }

  @SuppressWarnings("unchecked")
  public <E extends Event> List<E> applyEvent(E event) {

    Class currentClass = this.getClass();
    Class eventClass = event.getClass();

    // find method accepting event

    try {
      Method handler = currentClass.getDeclaredMethod("apply", eventClass);
      handler.invoke(this, event);

    } catch (Exception e) {
      wtf(e, "aggregate does not support events of type : %s", event.getClass());
    }

    return asList(event);
  }

  @SuppressWarnings("unchecked")
  public <C extends Command> List<Event> handleCommand(C command) throws BaseException{

    Class currentClass = this.getClass();
    Class commandClass = command.getClass();

    // find method accepting event

    try {
      Method handler = currentClass.getDeclaredMethod("handle", commandClass);
      return (List<Event>) handler.invoke(this, command);
    } catch (InvocationTargetException e){
      Throwable cause = e.getCause();

      if(cause instanceof BaseException){
        throw (BaseException) cause;
      }else{
        wtf(cause);
      }

    } catch (Exception e) {
      wtf(e, "aggregate does not support commands of type : %s", command.getClass());
    }

    return wtf();
  }
}