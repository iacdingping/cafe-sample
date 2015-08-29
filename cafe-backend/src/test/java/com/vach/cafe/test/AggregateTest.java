package com.vach.cafe.test;

import com.vach.cafe.server.Aggregate;
import com.vach.cafe.server.Command;
import com.vach.cafe.server.Event;
import com.vach.cafe.server.exception.CommandException;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static com.vach.cafe.util.Util.cast;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertTrue;


public class AggregateTest<T extends Aggregate> {


  protected void test(Supplier<T> given, Function<T, Object> when, Consumer<Object> then) {
    // test expected outcome
    then.accept(
        // execute when statement
        when.apply(
            // apply events to given aggregate
            given.get()
        )
    );
  }

  protected Supplier<T> given(T aggregate, Event... events) {
    return () -> {
      aggregate.applyEvents(asList(events));
      return aggregate;
    };
  }

  protected Function<T, Object> when(Command command) {
    return (aggregate) -> {
      try {
        return aggregate.handleCommand(command);
      } catch (CommandException e) {
        return e;
      }
    };
  }

  protected Consumer<Object> then(Event... expected) {

    return (a) -> {
      List<Event> actual = cast(a);

      for (Event event : expected) {
        assertTrue("event "+event+" is not present in actual result", actual.contains(event));
      }

    };
  }

  protected Consumer<Object> thenFailWith(Class<? extends CommandException> type) {
    return (a) -> assertTrue(type.isAssignableFrom(a.getClass()));
  }
}
