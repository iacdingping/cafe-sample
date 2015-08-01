package com.vach.cafe.server.command.bus;

import com.vach.cafe.server.Command;
import com.vach.cafe.server.Dispatcher;

/**
 * CommandDispatcher does not execute any business logic itself,
 * instead it delegates command to appropriate aggregate, which
 * encapsulates state and business logic associated with that state,
 * aggregate is the domain object tht will process the business logic.
 */
public class CommandDispatcher implements Dispatcher<Command> {


  // dispatcher

  @Override
  @SuppressWarnings("unchecked")
  public void dispatch(com.vach.cafe.server.Command command) {

  }

  // TODO implement batch command processing
}
