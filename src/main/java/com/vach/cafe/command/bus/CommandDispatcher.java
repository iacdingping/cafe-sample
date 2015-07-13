package com.vach.cafe.command.bus;

import com.vach.cafe.Command;
import com.vach.cafe.Dispatcher;

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
  public void dispatch(Command command) {

  }

  // TODO implement batch command processing
}
