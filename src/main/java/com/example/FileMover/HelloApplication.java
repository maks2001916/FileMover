package com.example.FileMover;
import javafx.application.Application;
import javafx.stage.Stage;
import ui.HomeWimdow;


public class HelloApplication extends Application {
    @Override
    public void start(Stage stage)  {

        HomeWimdow  homeWimdow = new HomeWimdow(stage);
        homeWimdow.start();
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}