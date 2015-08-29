package com.vach.cafe.server.bus;

/**
 * This is the message holder that is used in ring buffer.
 */
public class MessageWrapper {

  /**
   * Type of the message, when message is received  from network, this parameter
   * helps to detect what message on what version we have.
   *
   * e.g. SomeEvent:7 (means we have byte[] of SomeEvent on version 7)
   */
  public String type;

  /**
   * Binary representation of the message that is transmitted over the network.
   */
  public byte[] blob;

  /**
   * Actual object that is usable in application.
   */
  public Object object;
}