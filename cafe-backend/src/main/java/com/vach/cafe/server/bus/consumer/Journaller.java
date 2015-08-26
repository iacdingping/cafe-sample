package com.vach.cafe.server.bus.consumer;

public interface Journaller {
  void journal(byte[] blob);
}
