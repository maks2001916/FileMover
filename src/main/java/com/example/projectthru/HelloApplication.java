package com.example.projectthru;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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

        Label pathIn = new Label("исходная папка");
        gridPane.add(pathIn, 0, 0);
        TextField textPathIn = new TextField();
        gridPane.add(textPathIn, 1, 0);
        Button button = new Button("...");
        gridPane.add(button, 2, 0);

        Label pathOut = new Label("папка назначения");
        gridPane.add(pathOut, 0, 1);
        TextField textPathOut = new TextField();
        gridPane.add(textPathOut, 1, 1);
        Button button1 = new Button("...");
        gridPane.add(button1, 2, 1);

        Label start = new Label("переместить");
        gridPane.add(start, 0, 2);
        Button buttonStart = new Button("->");
        buttonStart.setPrefSize(130, 10);
        gridPane.add(buttonStart, 1, 2);


        //final String[] soursFilePath = new String[1];
        FileChooser fileChooser = new FileChooser();
        //final String[] destFilePath = new String[1];


        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                fileChooser.setTitle("выберите исходную деррикторию");
                final DirectoryChooser directoryChooser = new DirectoryChooser();
                final File selectedDirectory = directoryChooser.showDialog(stage);
                if (selectedDirectory != null) {
                    textPathIn.setText(selectedDirectory.getAbsolutePath());
                    //soursFilePath[0] = selectedDirectory.getAbsolutePath();
                }
            }

        });

        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                fileChooser.setTitle("выберите целевую деррикторию");
                final DirectoryChooser directoryChooser = new DirectoryChooser();
                final File selectedDirectory = directoryChooser.showDialog(stage);
                if (selectedDirectory != null) {
                    textPathOut.setText(selectedDirectory.getAbsolutePath());
                    //destFilePath[0] = selectedDirectory.getAbsolutePath();
                }
            }

        });

        Scene scene = new Scene(gridPane, 300, 200);
        stage.setScene(scene);

        buttonStart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                MoverService moverService = new MoverService();
                if (textPathIn != null && textPathIn.equals(textPathOut)) {
                } else {
                    moverService.traverseList(textPathIn.getText(), textPathOut.getText());
                }
                buttonStart.setText("перемещено");
            }
        });

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}