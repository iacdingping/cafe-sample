package com.vach.cafe;

public interface Bus<T extends Message> {

  void publish(T message);

  void publish(String type, byte[] data);

}
