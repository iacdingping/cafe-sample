package com.vach.cafe.server.bus.consumer;

public interface Replicator {
  void replicate(String type, byte[] data);
}
