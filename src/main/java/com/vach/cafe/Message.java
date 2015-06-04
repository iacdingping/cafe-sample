package com.vach.cafe;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public interface Message {

  ThreadLocal<Gson> GSON = ThreadLocal.withInitial(() -> new GsonBuilder().create());

  long timestamp();

  String type();
}
