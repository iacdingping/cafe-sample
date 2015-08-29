package com.vach.cafe.server;

/**
 * Bus (either CommandBuss or EventBus) shall be unique in scope of the application,
 * and shall be capable to properly delegate both object and serialized (blob)
 * messages to registered consumers.
 */
public interface Bus {

  void publish(String type, Message message);

  void publish(String type, byte[] data);

}
