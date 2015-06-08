package com.vach.cafe.test;

import com.vach.cafe.Aggregate;
import com.vach.cafe.Command;
import com.vach.cafe.Event;
import com.vach.cafe.exception.BaseException;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static com.vach.cafe.util.Util.cast;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
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
      } catch (BaseException e) {
        return e;
      }
    };
  }

  protected Consumer<Object> then(Event... expected) {

    return (a) -> {
        List<Event> actual = cast(a);
        assertTrue(asList(expected).containsAll(actual));
    };
  }

  protected Consumer<Object> then(Exception expected) {

    return (a) -> {
      Exception actual = cast(a);
      assertEquals(expected, actual);
    };
  }

//  protected Consumer<Object> thenFailWith(Class type){
//    return (a)-> Assert.assertTrue(type.isAssignableFrom(a.getClass()));
//  }
}
