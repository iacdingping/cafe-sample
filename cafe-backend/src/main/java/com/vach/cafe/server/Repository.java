package com.vach.cafe.server;

import java.util.Optional;

public interface Repository<K,V> {

  V get();

  Optional<V> get(K key);
}
