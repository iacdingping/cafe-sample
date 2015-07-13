package com.vach.cafe.sandbox.rx;

import java.util.List;

import rx.Observable;

public class StockServer {


  public static Observable<String> getFeed(List<String> symbols) {
    return Observable.create(subscriber -> {
      while (true){
//        symbols.stream()
//            .map(StockInfo::fetch)

      }
    });
  }
}
