package com.vach.cafe.event;

import com.vach.cafe.Event;

import static com.vach.cafe.encoder.TypeRegister.EVENT_TAB_CLOSED;

public class TabClosed extends Event{

  public TabClosed(long id) {
    super(id, EVENT_TAB_CLOSED);
  }




}
