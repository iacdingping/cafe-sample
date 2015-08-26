package com.vach.cafe.server.repo;

import com.vach.cafe.server.Command;
import com.vach.cafe.server.Event;
import com.vach.cafe.server.IHandleCommand;
import com.vach.cafe.server.Repository;
import com.vach.cafe.server.aggregate.tab.Tab;
import com.vach.cafe.server.util.ICanLog;
import com.vach.cafe.server.util.Util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * InMemory repository for Tab aggregates.
 *
 * NOTE : production grade repository shall use have off heap persistence, and preferably heap cache.
 */
public class TabRepo implements Repository<UUID, Tab>, IHandleCommand, ICanLog {

  private Map<UUID, Tab> map = new HashMap<>();

  @Override
  public Tab create(UUID id) {
    trace("creating new aggregate");

    // create new instance
    UUID key = UUID.randomUUID();
    Tab value = new Tab(key);

    // store in repository
    map.put(key, value);

    return value;
  }

  @Override
  public Tab get(UUID id) {
    trace("retrieving the aggregate : %s", id);

    if (map.containsKey(id)) {
      return map.get(id);
    } else {
      return null;
    }
  }

  @Override
  public List<Event> handleCommand(Command command) {
    // redirect command to appropriate aggregate
    return get(command.id).handleCommand(command);
  }

  @Override
  public List<Class<? extends Command>> getSupportedCommandTypes() {
    return Util.getSupportedCommandTypes(Tab.class);
  }
}
