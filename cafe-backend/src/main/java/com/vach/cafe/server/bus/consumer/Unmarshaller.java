package com.vach.cafe.server.bus.consumer;

public interface Unmarshaller {
  <T> T unmarshall(byte[] object);
}
