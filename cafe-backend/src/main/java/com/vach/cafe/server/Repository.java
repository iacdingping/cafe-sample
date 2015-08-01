package com.vach.cafe.server;

public interface Repository<T> {

  /**
   * Get aggregate instance from memory, or build latest version from event store, or create if does not exist.
   */
  T get(long id);

  /**
   * Make aggregate eligible for garbage collection
   */
  void remove(long id);

}
