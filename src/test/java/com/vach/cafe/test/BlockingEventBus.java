package com.vach.cafe.test;

import com.vach.cafe.Bus;
import com.vach.cafe.Dispatcher;
import com.vach.cafe.Event;
import com.vach.cafe.util.ICanLog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BlockingEventBus implements Bus<Event>, ICanLog {

  @Autowired
  Dispatcher<Event> dispatcher;

  @Override
  public void publish(Event event) {
    debug("passing to dispatcher");
    dispatcher.dispatch(event);
  }
}
