package com.example.projectthru;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import service.MoverService;

import java.io.File;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage)  {
        stage.setTitle("Программа перемещения файлов");
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.TOP_CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        Button button = new Button("выбрать исходную папку");
        HBox hBox = new HBox(10);
        hBox.setAlignment(Pos.BOTTOM_CENTER);
        hBox.getChildren().add(button);
        gridPane.add(hBox, 0, 1);

        Button button1 = new Button("выбрать папку назначения");
        HBox hBox1 = new HBox(10);
        hBox1.setAlignment(Pos.BOTTOM_CENTER);
        hBox1.getChildren().add(button1);
        gridPane.add(hBox1, 0, 2);

        Button buttonStart = new Button("начать перемещение");
        HBox hBoxStart = new HBox(10);
        hBoxStart.setAlignment(Pos.BOTTOM_CENTER);
        hBoxStart.getChildren().add(buttonStart);
        gridPane.add(hBoxStart, 0, 3);

        final String[] soursFilePath = new String[1];
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("выберите исходную деррикторию");

        final String[] destFilePath = new String[1];
        fileChooser.setTitle("выберите целевую деррикторию");

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                final DirectoryChooser directoryChooser = new DirectoryChooser();
                final File selectedDirectory = directoryChooser.showDialog(stage);
                if (selectedDirectory != null) {
                    soursFilePath[0] = selectedDirectory.getAbsolutePath();
                }
            }

        });

        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                final DirectoryChooser directoryChooser = new DirectoryChooser();
                final File selectedDirectory = directoryChooser.showDialog(stage);
                if (selectedDirectory != null) {
                    destFilePath[0] = selectedDirectory.getAbsolutePath();
                }
            }

        });

        Scene scene = new Scene(gridPane, 300, 200);
        stage.setScene(scene);

        buttonStart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                MoverService moverService = new MoverService();
                if (soursFilePath[0] != null) {
                    moverService.traverseList(soursFilePath[0], destFilePath[0]);
                }
            }
        });

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}