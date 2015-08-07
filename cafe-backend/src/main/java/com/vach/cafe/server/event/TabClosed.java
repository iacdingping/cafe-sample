package com.vach.cafe.server.event;

import com.vach.cafe.server.Event;

import java.util.UUID;

public class TabClosed extends Event {

  public double amountPaid;
  public double orderValue;
  public double tipValue;

  public TabClosed(UUID id, double amountPaid, double orderValue, double tipValue) {
    super(id);
    this.amountPaid = amountPaid;
    this.orderValue = orderValue;
    this.tipValue = tipValue;
  }
}
