package com.vach.cafe.aggregate.tab;

import com.vach.cafe.command.CloseTab;
import com.vach.cafe.command.MarkDrinksServed;
import com.vach.cafe.command.OpenTab;
import com.vach.cafe.command.PlaceOrder;
import com.vach.cafe.event.DrinksOrdered;
import com.vach.cafe.event.DrinksServed;
import com.vach.cafe.event.FoodOrdered;
import com.vach.cafe.event.TabClosed;
import com.vach.cafe.event.TabOpened;
import com.vach.cafe.exception.DrinksNotOutstanding;
import com.vach.cafe.exception.MustPayEnough;
import com.vach.cafe.exception.TabHasUnservedItems;
import com.vach.cafe.exception.TabIsOpen;
import com.vach.cafe.exception.TabNotOpen;
import com.vach.cafe.test.AggregateTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/context.xml"})
public class TabTests extends AggregateTest<Tab> {

  long testId;
  int testTable;
  String testWaiter;
  OrderedItem testDrink1;
  OrderedItem testDrink2;
  OrderedItem testFood1;
  OrderedItem testFood2;

  @Before
  public void setUp() {
    testId = 5;
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
            new MarkDrinksServed(testId, testDrink1.menuNumber, testDrink2.menuNumber)
        ),
        then(
            new DrinksServed(testId, testDrink1.menuNumber, testDrink2.menuNumber)
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
            new MarkDrinksServed(testId, testDrink2.menuNumber)
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
            new DrinksServed(testId, testDrink1.menuNumber)
        ),
        when(
            new MarkDrinksServed(testId, testDrink1.menuNumber)
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
            new DrinksServed(testId, testDrink1.menuNumber)
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
            new DrinksServed(testId, testDrink1.menuNumber)
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