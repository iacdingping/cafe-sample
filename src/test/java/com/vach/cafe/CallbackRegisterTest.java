package com.vach.cafe;

import org.junit.Test;

import static com.vach.cafe.util.Util.bearSleep;
import static com.vach.cafe.util.Util.log;

public class CallbackRegisterTest {

  @Test
  public void shallBeReusable() {

    CallbackRegister register = new CallbackRegister();

    new Thread(()->{
      bearSleep(2000);
      register.fail(new IllegalStateException());
    }).start();

    register
        .onSuccess(result -> log("SUCCESS!!!"))
        .onFailure(cause -> log("FAILURE"))
        .waitForResult();

    new Thread(()->{
      bearSleep(2000);
      register.success("Yay!");
    }).start();

    register
        .onSuccess(result -> log("SUCCESS!!!"))
        .onFailure(cause -> log("FAILURE"))
        .waitForResult();
  }


  @Test
  public void shallIgnoreIfNoSubscribers() {

    CallbackRegister register = new CallbackRegister();

    new Thread(()->{
      bearSleep(2000);
      register.fail(new IllegalStateException());
    }).start();

    bearSleep(3000);

  }
}