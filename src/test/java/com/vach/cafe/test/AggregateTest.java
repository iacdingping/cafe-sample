package com.vach.cafe.test;

import com.vach.cafe.Aggregate;
import com.vach.cafe.Command;
import com.vach.cafe.Event;

import org.junit.Assert;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static com.vach.cafe.util.Util.cast;
import static java.util.Arrays.asList;


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
      } catch (Exception e) {
        return e;
      }
    };
  }

  protected Consumer<Object> then(Event... expected) {

    return (a) -> {
      List<Event> actual = cast(a);
      Assert.assertEquals(asList(expected), actual);
    };
  }

  protected Consumer<Object> then(Exception expected) {

    return (a) -> {
      Exception actual = cast(a);
      Assert.assertEquals(expected, actual);
    };
  }

  protected Consumer<Object> thenFailWith(Class type){
    return (a)-> Assert.assertTrue(type.isAssignableFrom(a.getClass()));
  }
}
