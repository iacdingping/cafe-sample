package com.vach.cafe.aggregate.tab;

import com.vach.cafe.command.OpenTab;
import com.vach.cafe.command.PlaceOrder;
import com.vach.cafe.event.DrinksOrdered;
import com.vach.cafe.event.FoodOrdered;
import com.vach.cafe.event.TabOpened;
import com.vach.cafe.exception.TabNotOpen;
import com.vach.cafe.test.AggregateTester;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static java.util.Arrays.asList;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/context.xml"})
public class TabTests {

  @Autowired
  AggregateTester<Tab> tester;

  @Test
  public void shouldPublishTabOpenedEvent() {
    tester
        .given()
        .when(new OpenTab(0, 10, "margo"))
        .then(new TabOpened(0, 10, "margo"));
  }

  @Test
  public void canNotOrderWithUnopenedTab() {
    tester
        .given()
        .when(
            new PlaceOrder(
                0,
                asList(
                    new OrderedItem(0, 15.5, false, "burrito"),
                    new OrderedItem(0, 2.35, true, "cola")
                )
            )
        )
        .then(TabNotOpen.class);
  }

  @Test
  public void shouldPublishTwoEvents() {
    tester
        .given(
            new TabOpened(0, 10, "margo")
        )
        .when(
            new PlaceOrder(
                0,
                asList(
                    new OrderedItem(0, 15.5, false, "burrito"),
                    new OrderedItem(0, 2.35, true, "cola")
                )
            )
        )
        .then(
            new DrinksOrdered(0, asList(new OrderedItem(0, 2.35, true, "cola"))),
            new FoodOrdered(0, asList(new OrderedItem(0, 15.5, false, "burrito")))
        );
  }


  @Test
  public void testSimple() {



  }
}