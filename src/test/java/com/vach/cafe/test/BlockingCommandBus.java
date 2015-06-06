package com.vach.cafe.test;

import com.vach.cafe.Bus;
import com.vach.cafe.Command;
import com.vach.cafe.Dispatcher;
import com.vach.cafe.util.ICanLog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BlockingCommandBus implements Bus<Command>, ICanLog{

  @Autowired
  Dispatcher<Command> dispatcher;

  @Override
  public void publish(Command command) {
    debug("passing to dispatcher");
    dispatcher.dispatch(command);
  }
}
