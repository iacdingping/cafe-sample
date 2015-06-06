package com.vach.cafe.command;

import com.vach.cafe.Command;
import com.vach.cafe.CommandHandler;
import com.vach.cafe.Event;
import com.vach.cafe.exception.TabIsOpen;

import java.util.List;


public class OpenTab extends Command {

  public interface IHandleOpenTab extends CommandHandler<OpenTab> {
    List<Event> handle(OpenTab command) throws TabIsOpen;
  }

  public int tableNumber;
  public String waiter;

  public OpenTab(long id, int tableNumber, String waiter) {
    super(id);
    this.tableNumber = tableNumber;
    this.waiter = waiter;
  }

}
