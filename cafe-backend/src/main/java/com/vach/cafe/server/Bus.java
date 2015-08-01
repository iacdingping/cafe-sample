package com.vach.cafe.server;

public interface Bus<T extends Message> {

  void publish(T message);

}
