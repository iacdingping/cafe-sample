package com.vach.cafe.server.command.bus;

import com.vach.cafe.server.Command;
import com.vach.cafe.server.bus.Dispatcher;

/**
 * CommandDispatcher does not execute any business logic itself,
 * instead it delegates command to appropriate handler (aggregate), which
 * encapsulates state and business logic associated with that state,
 * aggregate is the domain object that will process the business logic.
 */
public class CommandDispatcher implements Dispatcher<Command> {

  @Override
  public void dispatch(Command command) {

  }
}
