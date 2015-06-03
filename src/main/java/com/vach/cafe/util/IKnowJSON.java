package com.vach.cafe.util;

import static com.vach.cafe.util.Util.wtf;

public interface IKnowJSON {

  /**
   * Marshall message data to json
   */
  default byte[] marshall(){
    return wtf();
  }

  /**
   * UnMarshall message data from json
   */
  default void unMarshall(byte[] data){
    wtf();
  }
}
