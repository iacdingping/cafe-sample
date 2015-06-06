package com.vach.cafe.test;

import com.vach.cafe.Aggregate;
import com.vach.cafe.Command;
import com.vach.cafe.Event;
import com.vach.cafe.Repository;
import com.vach.cafe.command.bus.CommandDispatcher;
import com.vach.cafe.util.ICanLog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.vach.cafe.util.Validator.compareIgnoreOrder;
import static org.junit.Assert.assertTrue;

/**
 * AggregateTester is a helper entity for aggregate unit testing
 */
@Component
public class AggregateTester<A extends Aggregate> implements ICanLog {

  @Autowired
  CommandDispatcher commandDispatcher;

  @Autowired
  TestEventDispatcher eventDispatcher;

  @Autowired
  Repository<A> repository;

  private Event[] emittedEvents;
  private Exception[] thrownExceptions;

  public AggregateTester given() {
    return this;
  }

  public AggregateTester given(Event... events) {

    for (Event event : events) {
      repository.get(event.aggregateId()).load(event);
    }

    return this;
  }

  public AggregateTester when(Command... commands) {
    List<Exception> exceptions = new ArrayList<>();

    for (Command command : commands) {
      command.progress().onFailure(exceptions::add);
      commandDispatcher.dispatch(command);
    }

    emittedEvents = eventDispatcher.events();
    thrownExceptions = exceptions.toArray(new Exception[exceptions.size()]);

    return this;
  }

  // terminal operations

  public void then(Event... expectedEvents) {
    assertTrue(
        compareIgnoreOrder(
            expectedEvents,
            emittedEvents
        )
    );
  }

  public void then(Class<Exception> expectedExceptionType) {
    assertTrue(thrownExceptions.length == 1);
    assertTrue(thrownExceptions[0].getClass().equals(expectedExceptionType));
  }
}
