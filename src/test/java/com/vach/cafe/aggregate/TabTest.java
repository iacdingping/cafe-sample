package com.vach.cafe.aggregate;


import com.vach.cafe.util.ICanLog;

import org.junit.Test;

import static com.vach.cafe.util.Util.bearSleep;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"/context.xml"})
public class TabTest implements ICanLog{


  @Test
  public void testOpenTab() {

    info("something");

    bearSleep(100);
  }
}