package com.vach.cafe.server.repo;

import com.vach.cafe.server.IHandleCommand;
import com.vach.cafe.server.Repository;
import com.vach.cafe.server.aggregate.tab.Tab;
import com.vach.cafe.server.util.ICanLog;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * InMemory repository for Tab aggregates.
 *
 * NOTE : production grade repository shall use have off heap persistence,
 * and preferably heap cache.
 */
public class TabRepo implements Repository<UUID, Tab>, IHandleCommand, ICanLog {

  private Map<UUID, Tab> map = new HashMap<>();

  @Override
  public Tab get() {
    trace("creating new aggregate");

    // create new instance
    UUID key = UUID.randomUUID();
    Tab value = new Tab(key);

    // store in repository
    map.put(key, value);

    return value;
  }

  @Override
  public Optional<Tab> get(UUID id) {
    trace("retrieving the aggregate : %s", id);

    if (map.containsKey(id)) {
      return Optional.of(map.get(id));
    } else {
      return Optional.empty();
    }
  }
}
