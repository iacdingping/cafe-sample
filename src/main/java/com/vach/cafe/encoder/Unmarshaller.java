package com.vach.cafe.encoder;

import com.vach.cafe.Message;

import static com.vach.cafe.util.Util.wtf;

public class Unmarshaller {

  public static <T extends Message> T unmarshal(String type, byte[] data) {
    return wtf(); // TODO implement unmarshaller
  }

}
