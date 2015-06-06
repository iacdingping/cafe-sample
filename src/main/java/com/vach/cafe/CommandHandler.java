package com.vach.cafe;

import com.vach.cafe.util.ICanLog;

import java.util.List;

public interface CommandHandler<T extends Command> extends ICanLog {

  List<Event> handle(T command) throws Exception;
}
