package com.vach.cafe;

import com.vach.cafe.exception.CommandException;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import static com.vach.cafe.util.Util.wtf;

/**
 * Command handler interface.
 *
 * NOTE : default implementation will use reflection and rely on convention to invoke appropriate handler method.
 */
public interface IHandleCommand {


  /**
   * Annotation for CommandHandler methods.
   *
   * CommandHandlers must accept instance of Command, optionally can throw CommandException
   * be accessible and return list of Events triggered during the executions.
   */
  @Target(ElementType.METHOD)
  @Retention(RetentionPolicy.RUNTIME)
  @interface CommandHandler {
  }

  @SuppressWarnings("unchecked")
  default <C extends Command> List<Event> handleCommand(C command) throws CommandException {

    Class currentClass = this.getClass();
    Class commandClass = command.getClass();

    // find method accepting event

    try {
      Method handler = currentClass.getDeclaredMethod("handle", commandClass);

      assert handler.getAnnotation(CommandHandler.class) != null;

      return (List<Event>) handler.invoke(this, command);
    } catch (InvocationTargetException e) {
      Throwable cause = e.getCause();

      if (cause instanceof CommandException) {
        throw (CommandException) cause;
      } else {
        wtf(cause);
      }

    } catch (Exception e) {
      wtf(e, "aggregate does not support commands of type : %s", command.getClass());
    }

    return wtf();
  }
}

