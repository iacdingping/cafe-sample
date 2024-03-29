package com.vach.cafe.server.aggregate.tab;


import com.vach.cafe.server.command.CloseTab;
import com.vach.cafe.server.command.MarkDrinksServed;
import com.vach.cafe.server.command.OpenTab;
import com.vach.cafe.server.command.PlaceOrder;
import com.vach.cafe.server.event.DrinksOrdered;
import com.vach.cafe.server.event.DrinksServed;
import com.vach.cafe.server.event.FoodOrdered;
import com.vach.cafe.server.event.TabClosed;
import com.vach.cafe.server.event.TabOpened;
import com.vach.cafe.server.exception.DrinksNotOutstanding;
import com.vach.cafe.server.exception.MustPayEnough;
import com.vach.cafe.server.exception.TabHasUnservedItems;
import com.vach.cafe.server.exception.TabIsOpen;
import com.vach.cafe.server.exception.TabNotOpen;
import com.vach.cafe.test.AggregateTest;

import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"/context.xml"})
public class TabTests extends AggregateTest<Tab> {

  UUID testId;
  int testTable;
  String testWaiter;
  OrderedItem testDrink1;
  OrderedItem testDrink2;
  OrderedItem testFood1;
  OrderedItem testFood2;

  @Before
  public void setUp() {
    testId = UUID.randomUUID();
    testTable = 11;
    testWaiter = "john";
    testDrink1 = new OrderedItem(5, 2.45, true, "cola");
    testDrink2 = new OrderedItem(4, 5.1, true, "beer");
    testFood1 = new OrderedItem(16, 15.3, false, "soup");
    testFood2 = new OrderedItem(17, 8.5, false, "salad");
  }

  @Test
  public void canOpenNewTab() {
    test(
        given(new Tab()),
        when(
            new OpenTab(testId, testTable, testWaiter)
        ),
        then(
            new TabOpened(testId, testTable, testWaiter)
        )
    );
  }

  @Test
  public void canNotOpenAlreadyOpenedTab() {
    test(
        given(
            new Tab(),
            new TabOpened(testId, testTable, testWaiter)
        ),
        when(
            new OpenTab(testId, testTable, testWaiter)
        ),
        thenFailWith(
            TabIsOpen.class
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
            new PlaceOrder(testId, testDrink1)
        ),
        thenFailWith(
            TabNotOpen.class
        )
    );
  }

  @Test
  public void canPlaceDrinksOrder() {
    test(
        given(
            new Tab(),
            new TabOpened(testId, testTable, testWaiter)
        ),
        when(
            new PlaceOrder(testId, testDrink1, testDrink2)
        ),
        then(
            new DrinksOrdered(testId, testDrink1, testDrink2)
        )
    );
  }

  @Test
  public void canPlaceFoodOrder() {
    test(
        given(
            new Tab(),
            new TabOpened(testId, testTable, testWaiter)
        ),
        when(
            new PlaceOrder(testId, testFood1, testFood2)
        ),
        then(
            new FoodOrdered(testId, testFood1, testFood2)
        )
    );
  }

  @Test
  public void canPlaceFoodAndDrinkOrder() {
    test(
        given(
            new Tab(),
            new TabOpened(testId, testTable, testWaiter)
        ),
        when(
            new PlaceOrder(testId, testFood1, testDrink1)
        ),
        then(
            new FoodOrdered(testId, testFood1),
            new DrinksOrdered(testId, testDrink1)
        )
    );
  }

  @Test
  public void orderedDrinksCanBeServed() {
    test(
        given(
            new Tab(),
            new TabOpened(testId, testTable, testWaiter),
            new DrinksOrdered(testId, testDrink1, testDrink2)
        ),
        when(
            new MarkDrinksServed(testId, testDrink1.menuNumber(), testDrink2.menuNumber())
        ),
        then(
            new DrinksServed(testId, testDrink1.menuNumber(), testDrink2.menuNumber())
        )
    );
  }

  @Test
  public void cannotServeUnorderedDrink() {
    test(
        given(
            new Tab(),
            new TabOpened(testId, testTable, testWaiter),
            new DrinksOrdered(testId, testDrink1)
        ),
        when(
            new MarkDrinksServed(testId, testDrink2.menuNumber())
        ),
        thenFailWith(
            DrinksNotOutstanding.class
        )
    );
  }

  @Test
  public void canNotServeAnOrderedDrinkTwice() {
    test(
        given(
            new Tab(),
            new TabOpened(testId, testTable, testWaiter),
            new DrinksOrdered(testId, testDrink1),
            new DrinksServed(testId, testDrink1.menuNumber())
        ),
        when(
            new MarkDrinksServed(testId, testDrink1.menuNumber())
        ),
        thenFailWith(
            DrinksNotOutstanding.class
        )
    );
  }

  @Test
  public void canCloseTabWithTip() {
    test(
        given(
            new Tab(),
            new TabOpened(testId, testTable, testWaiter),
            new DrinksOrdered(testId, testDrink1),
            new DrinksServed(testId, testDrink1.menuNumber())
        ),
        when(
            new CloseTab(testId, 3)
        ),
        then(
            new TabClosed(testId, 3, 2.45, 3 - 2.45)
        )
    );
  }

  @Test
  public void mustPayEnough() {
    test(
        given(
            new Tab(),
            new TabOpened(testId, testTable, testWaiter),
            new DrinksOrdered(testId, testDrink1),
            new DrinksServed(testId, testDrink1.menuNumber())
        ),
        when(
            new CloseTab(testId, 2)
        ),
        thenFailWith(
            MustPayEnough.class
        )
    );
  }

  @Test
  public void cantCloseTabUntilAllOrdersAreServed() {
    test(
        given(
            new Tab(),
            new TabOpened(testId, testTable, testWaiter),
            new DrinksOrdered(testId, testDrink1)
        ),
        when(
            new CloseTab(testId, 3)
        ),
        thenFailWith(
            TabHasUnservedItems.class
        )
    );
  }


}