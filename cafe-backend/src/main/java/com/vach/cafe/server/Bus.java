package com.vach.cafe.server;

public interface Bus {

  void publish(String type, Message message);

  void publish(String type, byte[] data);

}
