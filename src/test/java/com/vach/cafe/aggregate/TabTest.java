package com.vach.cafe.aggregate;


import com.vach.cafe.aggregate.tab.Tab;
import com.vach.cafe.test.AggregateTester;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/context.xml"})
public class TabTest extends AggregateTester<Tab>{
  @Autowired
  Tab tab;

  @Test
  public void test() {



  }
}