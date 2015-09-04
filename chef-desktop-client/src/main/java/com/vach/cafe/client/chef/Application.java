package com.vach.cafe.client.chef;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Lazy;

import javafx.scene.Scene;
import javafx.stage.Stage;

@Lazy
@SpringBootApplication
public class Application extends AbstractJavaFxApplicationSupport {

    @Value("${ui.title}")
    private String windowTitle;

    @Autowired
    @Qualifier("mainView")
    private AppContext.View view;

    @Autowired
    private CafeWebsocketClient service;

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle(windowTitle);
        stage.setScene(new Scene(view.getView()));
        stage.setResizable(true);
        stage.centerOnScreen();
        stage.show();


    }

    public static void main(String[] args) {
        launchApp(Application.class, args);
    }

}
