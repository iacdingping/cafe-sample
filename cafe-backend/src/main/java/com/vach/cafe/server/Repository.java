package com.vach.cafe.server;

public interface Repository<K, V> {

  V create(K key);

  V get(K key);
}
