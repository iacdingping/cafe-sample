package com.vach.cafe.sandbox.rx;

import java.util.HashMap;
import java.util.Map;

public class StockInfo {

  private static Map<String, Double> lastPrices = new HashMap<>();

  public static StockInfo fetch(String symbol) {
    return new StockInfo(symbol, priceOf(symbol));
  }

  private static double priceOf(String symbol) {

    // fetch last known price
    Double lastPrice = lastPrices.get(symbol);

    // if none, randomly generate initial price
    if (lastPrice == null) {
      double randomPrice = Math.random() * 100;
      lastPrices.put(symbol, randomPrice);
      return randomPrice;
    }

    // generate new price
    double newPrice = Math.abs(lastPrice + (Math.random() - 0.5) * 5);
    lastPrices.put(symbol, newPrice);
    return newPrice;
  }

  public static void main(String[] args) {
    for (int i = 0; i < 100; i++) {
      System.out.println(priceOf("abc"));
    }
  }


  String ticker;
  double value;

  public StockInfo(String ticker, double value) {
    this.ticker = ticker;
    this.value = value;
  }
}
