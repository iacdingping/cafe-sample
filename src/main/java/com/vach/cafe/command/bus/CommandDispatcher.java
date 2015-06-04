package com.vach.cafe.command.bus;

import com.vach.cafe.Command;
import com.vach.cafe.CommandHandler;
import com.vach.cafe.Dispatcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.vach.cafe.util.Util.wtf;

/**
 * CommandDispatcher does not execute any business logic itself, instead it delegates command to appropriate handler.
 */
@Component
public class CommandDispatcher implements Dispatcher<Command> {

  private Map<Class, CommandHandler> handlers = new HashMap<>();

  @Autowired
  public CommandDispatcher(List<CommandHandler> handlers) {
    debug("registering handlers");
    for (CommandHandler handler : handlers) {
      trace("handler : %s", handler.getClass().getSimpleName());
      this.handlers.put(handler.type(), handler);
    }
  }

  // dispatcher

  @Override
  @SuppressWarnings("unchecked")
  public void dispatch(Command command) {
    trace("dispatching command : %s", command);

    CommandHandler handler = handlers.get(command.getClass());

    if (handler == null) {
      wtf("no handler is registered for type : %s", command.getClass());
    }

    handler.on(command);
  }


  @Override
  public void dispatch(List<Command> commands) {

    // TODO implement batch processing logic
    commands.forEach(this::dispatch);
  }
}
