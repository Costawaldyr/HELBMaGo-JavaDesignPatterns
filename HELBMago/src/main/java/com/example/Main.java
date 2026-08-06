package com.example;

import javafx.stage.Stage;
import javafx.application.Application;

import com.example.controller.MainController;

/*
*/
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        MainController controller = new MainController(primaryStage);
    }

    public static void main( String[] args ) {
        launch(args);
    }
}