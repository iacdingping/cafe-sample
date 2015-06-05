package com.vach.cafe.aggregate.tab.handler.command;

import com.vach.cafe.CommandHandler;
import com.vach.cafe.Repository;
import com.vach.cafe.aggregate.tab.Tab;
import com.vach.cafe.command.OpenTab;
import com.vach.cafe.event.TabOpened;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OpenTabHandler implements CommandHandler<OpenTab> {

  @Autowired
  Repository<Tab> repo;

  @Override
  public Class<OpenTab> type() {
    return OpenTab.class;
  }

  @Override
  public void on(OpenTab command) {
    info("handling : %s", command);

    Tab tab = repo.get(command.aggregateId());

    tab.apply(
        new TabOpened(
            command.aggregateId(),
            command.tableNumber,
            command.waiter
        )
    );

    command.progress().success();
  }
}
