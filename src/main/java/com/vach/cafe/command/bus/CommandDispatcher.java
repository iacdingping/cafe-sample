package com.vach.cafe.command.bus;

import com.vach.cafe.Command;
import com.vach.cafe.Dispatcher;

/**
 * CommandDispatcher does not execute any business logic itself, instead it delegates command to appropriate handler.
 */
public class CommandDispatcher implements Dispatcher<Command> {


  // dispatcher

  @Override
  @SuppressWarnings("unchecked")
  public void dispatch(Command command) {

  }

  // TODO implement batch command processing
}
