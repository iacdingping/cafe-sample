package com.vach.cafe.aggregate.tab;

import com.vach.cafe.command.OpenTab;
import com.vach.cafe.command.PlaceOrder;
import com.vach.cafe.event.TabOpened;
import com.vach.cafe.exception.TabIsOpen;
import com.vach.cafe.exception.TabNotOpen;
import com.vach.cafe.test.AggregateTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static java.util.Arrays.asList;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/context.xml"})
public class TabTests extends AggregateTest<Tab> {

  long testId;
  int testTable;
  String testWaiter;
  OrderedItem testDrink1;

  @Before
  public void setUp() {
    testId = 5;
    testTable = 11;
    testWaiter = "john";
    testDrink1 = new OrderedItem(5, 2.45, true, "cola");
  }

  @Test
  public void canOpenNewTab() {
    test(
        given(new Tab()),
        when(
            new OpenTab(
                testId,
                testTable,
                testWaiter
            )
        ),
        then(
            new TabOpened(
                testId,
                testTable,
                testWaiter
            )
        )
    );
  }

  @Test
  public void canNotOpenAlreadyOpenedTab() {
    test(
        given(
            new Tab(),
            new TabOpened(
                testId,
                testTable,
                testWaiter
            )
        ),
        when(
            new OpenTab(
                testId,
                testTable,
                testWaiter
            )
        ),
        then(
            new TabIsOpen()
        )
    );
  }

  @Test
  public void cannotPlaceOrderWithUnopenedTab() {
    test(
        given(
            new Tab()
        ),
        when(
            new PlaceOrder(
                testId,
                asList(testDrink1)
            )
        ),
        then(
            new TabNotOpen()
        )
    );
  }

}