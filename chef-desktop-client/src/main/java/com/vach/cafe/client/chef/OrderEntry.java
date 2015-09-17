package com.vach.cafe.client.chef;

import java.io.Serializable;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

public class OrderEntry implements Serializable {

  private SimpleStringProperty tableId;
  private SimpleStringProperty order;

  public OrderEntry(int table, String order) {
    this.tableId = new SimpleStringProperty("table : " + table);
    this.order = new SimpleStringProperty(order);
  }

  public ObservableValue<String> table() {
    return tableId;
  }

  public ObservableValue<String> order() {
    return order;
  }

//  public ObservableValue<String> cook() {
//    return new SimpleStringProperty("cook");
//  }
//
//  public ObservableValue<String> time() {
//    return new SimpleStringProperty("time");
//  }
}
