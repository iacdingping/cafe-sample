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

import static com.vach.cafe.util.Util.asArray;
import static org.junit.Assert.assertArrayEquals;

@Component
public class AggregateTester<A extends Aggregate> implements ICanLog{

  @Autowired
  CommandDispatcher commandDispatcher;

  @Autowired
  TestEventDispatcher eventDispatcher;

  @Autowired
  Repository<A> repository;

  private Event[] givenEvents;
  private Command[] givenCommands;

  private A aggregate;

  private Event[] emittedEvents;
  private Exception[] thrownExceptions;

  public AggregateTester given(){
    return this;
  }

  public AggregateTester given(Event... events){
    this.givenEvents = events;

    aggregate.load(events);

    return this;
  }

  public AggregateTester when(Command... commands){
    this.givenCommands = commands;
    List<Exception> exceptions = new ArrayList<>();

    for (Command command : givenCommands) {
      command.progress().onFailure(exceptions::add);
      commandDispatcher.dispatch(command);
    }

    emittedEvents = eventDispatcher.events();
    thrownExceptions = asArray(exceptions);

    return this;
  }

  // terminal operations

  public void then(Event... expectedEvents){
    assertArrayEquals(
        expectedEvents,
        emittedEvents
    );
  }

  public void then(Exception... expectedExceptions){
    assertArrayEquals(
        expectedExceptions,
        thrownExceptions
    );
  }
}
