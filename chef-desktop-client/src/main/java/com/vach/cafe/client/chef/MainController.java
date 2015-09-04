package com.vach.cafe.client.chef;

import java.util.LinkedList;

import javax.annotation.PostConstruct;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class MainController {


  @FXML private TableColumn<OrderEntry,String> tableCol;
  @FXML private TableColumn<OrderEntry,String> orderCol;
  @FXML private TableView<OrderEntry> table;


  private ObservableList<OrderEntry> data;

  @FXML
  public void initialize() {
  }

  @SuppressWarnings("unchecked")
  @PostConstruct
  public void init() {
    data = FXCollections.observableArrayList(new LinkedList<>());

    data.addAll(
        new OrderEntry(1, "order 1"),
        new OrderEntry(2, "order 2"),
        new OrderEntry(3, "order 3")
    );

    tableCol.setCellValueFactory(param -> param.getValue().table());
    orderCol.setCellValueFactory(param -> param.getValue().order());

    table.getColumns().addAll(tableCol, orderCol);



    table.setItems(data);
  }

  public void toggleConnection(ActionEvent actionEvent) {

  }
}
