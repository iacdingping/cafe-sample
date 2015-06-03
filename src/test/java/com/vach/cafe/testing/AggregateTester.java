package com.vach.cafe.testing;

import com.vach.cafe.Aggregate;
import com.vach.cafe.Command;
import com.vach.cafe.Event;
import com.vach.cafe.command.bus.CommandDispatcher;
import com.vach.cafe.event.bus.EventDispatcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AggregateTester<A extends Aggregate> {

  @Autowired
  CommandDispatcher commandDispatcher;

  @Autowired
  EventDispatcher eventDispatcher;

  A aggregate;
  Event[] givenEvents;
  Command command;
  Event[] expectedEvents;
  Exception expectedException;

  void given(A aggregate, Event... events){
    this.aggregate = aggregate;
    aggregate.load(events);
  }

  void when(Command command){
    commandDispatcher.dispatch(command);
  }

  void then(Event... events){

  }

  void then(Exception exception){

  }
}
