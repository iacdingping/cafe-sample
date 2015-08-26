package com.vach.cafe.server.bus.consumer;

public interface Marshaller {
  byte[] marshall(Object object);
}
