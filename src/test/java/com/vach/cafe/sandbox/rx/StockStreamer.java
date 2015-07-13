package com.vach.cafe.sandbox.rx;

import java.util.List;

import rx.Observable;

import static java.util.Arrays.asList;

public class StockStreamer {

  public static void main(String[] args) {
    List<String> symbols = asList("GOOG", "AMZN", "ORCL", "APPL");

    Observable<String> feed = StockServer.getFeed(symbols);

    feed.subscribe(System.out::println);
  }

}
