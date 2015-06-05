package com.vach.cafe;

public interface Message {

//  ThreadLocal<Gson> GSON = ThreadLocal.withInitial(() -> new GsonBuilder().create());

  long timestamp();

  String type();
}
