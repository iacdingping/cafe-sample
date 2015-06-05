package com.vach.cafe.aggregate.tab.repo;

import com.vach.cafe.Repository;
import com.vach.cafe.aggregate.tab.Tab;
import com.vach.cafe.util.ICanLog;

import gnu.trove.map.hash.TLongObjectHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.lang.ref.SoftReference;

import static com.vach.cafe.util.Util.wtf;

public class TabRepo implements Repository<Tab>, ICanLog {

  @Autowired
  ApplicationContext context;

  private TLongObjectHashMap<Tab> map = new TLongObjectHashMap<>();
  private TLongObjectHashMap<SoftReference<Tab>> cache = new TLongObjectHashMap<>();

  @Override
  public Tab get(long id) {
    trace("retrieving the aggregate : %s", id);

    Tab aggregate = map.get(id);

    // if not in memory try the cache
    if (aggregate == null) {
      aggregate = getFromCache(id);
    }

    // if not in memory get from event store
    if (aggregate == null) {
      aggregate = getFromEventStore(id);
    }

    // create if does not exist
    if (aggregate == null) {
      aggregate = context.getBean(Tab.class);
    }

    return aggregate;
  }

  @Override
  public void remove(long id) {
    trace("moving aggregate to reclaimable cache : %s", id);

    cache.put(id, new SoftReference<>(map.remove(id)));
  }

  /**
   * Tries to get value from SoftReference cache, if value exists its moved to regular repo.
   */
  private Tab getFromCache(long id) {
    SoftReference<Tab> reference = cache.remove(id);

    if (reference != null) {
      Tab aggregate = reference.get();

      if (aggregate != null) {
        trace("got aggregate from cache : %s", id);
        map.put(id, aggregate);
        return aggregate;
      }
    }

    return null;
  }

  /**
   * Rebuild aggregate from event store.
   */
  private Tab getFromEventStore(long id) {
    // replay events from event store
    wtf("not implemented yet");

    trace("got aggregate from event store : %s", id);
    return null;
  }
}
