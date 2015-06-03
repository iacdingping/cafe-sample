package com.vach.cafe;

public interface Repository<T> {

  /**
   * Get aggregate instance from memory
   * or build latest version from event store
   */
  T get(long id);

  /**
   * Make aggregate eligible for garbage collection
   */
  void remove(long id);

}
