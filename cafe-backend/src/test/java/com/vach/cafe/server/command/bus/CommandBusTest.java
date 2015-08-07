package com.vach.cafe.server.command.bus;

import org.junit.Test;

public class CommandBusTest {

  @Test
  public void test() {

    // init aggregate repositories
    // init command dispatcher
    // create command bus

    /**
     * when a command is published to the command bus,
     * first it is taken by deserializer which converts string to Command instance,
     * then it is taken by command dispatcher,
     * which is looking up for appropriate aggregate from repositories to handle the command.
     *
     * Aggregate can take commands/events in batch. Concurrency faults are avoided with disruptor,
     * which ensures no concurrent call to same aggregate will take place.
     *
     * Commands on the same aggregate must be executed in correct order. What if few aggregates shall maintain
     * happens before with command orders
     */
  }
}