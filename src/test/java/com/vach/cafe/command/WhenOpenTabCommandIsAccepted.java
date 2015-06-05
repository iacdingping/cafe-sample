package com.vach.cafe.command;

import com.vach.cafe.aggregate.tab.Tab;
import com.vach.cafe.event.TabOpened;
import com.vach.cafe.test.AggregateTester;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/context.xml"})
public class WhenOpenTabCommandIsAccepted {
  @Autowired
  AggregateTester<Tab> tester;

  @Test
  public void ShouldPublishTabOpenedEvent() {
    tester
        .given()
        .when(new OpenTab(0, 10, "margo"))
        .then(new TabOpened(0, 10, "margo"));
  }
}