package com.example.FileMover;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import service.MoverService;

import java.io.*;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage)  {

        MoverService moverService = new MoverService();
        moverService.getListLanguages();

        stage.setTitle(moverService.getProperties("title"));
        stage.setWidth(415);
        stage.setHeight(160);
        stage.setResizable(true);
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.TOP_CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        Label pathIn = new Label(moverService.getProperties("pathIn"));
        gridPane.add(pathIn, 0, 0);
        TextField textPathIn = new TextField();
        gridPane.add(textPathIn, 1, 0);
        Button button = new Button("...");
        button.setMinWidth(60);
        gridPane.add(button, 2, 0);

        Label pathOut = new Label(moverService.getProperties("pathOut"));
        gridPane.add(pathOut, 0, 1);
        TextField textPathOut = new TextField();
        gridPane.add(textPathOut, 1, 1);
        Button button1 = new Button("...");
        button1.setMinWidth(60);
        gridPane.add(button1, 2, 1);

        Label start = new Label(moverService.getProperties("move"));
        gridPane.add(start, 0, 2);
        Button buttonStart = new Button("->");
        buttonStart.setPrefSize(200, 10);
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.BOTTOM_CENTER);
        hBox.getChildren().add(buttonStart);
        hBox.setPrefSize(200, 10);
        gridPane.add(hBox, 1, 2);

        ProgressBar progressBar = new ProgressBar();
        gridPane.add(progressBar, 1, 3);
        progressBar.setMinWidth(200);

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                final DirectoryChooser directoryChooser = new DirectoryChooser();
                directoryChooser.setTitle(moverService.getProperties("selectTheSourceDirectory"));
                final File selectedDirectory = directoryChooser.showDialog(stage);
                if (selectedDirectory != null) {
                    textPathIn.setText(selectedDirectory.getAbsolutePath());
                }
            }

        });

        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                final DirectoryChooser directoryChooser = new DirectoryChooser();
                directoryChooser.setTitle(moverService.getProperties("selectTheTargetAudience"));
                final File selectedDirectory = directoryChooser.showDialog(stage);
                if (selectedDirectory != null) {
                    textPathOut.setText(selectedDirectory.getAbsolutePath());
                }
            }

        });

        Scene scene = new Scene(gridPane, 300, 200);
        stage.setScene(scene);

        String fontSheet = moverService.fileToStylesheetString(new File (
                "/run/media/deck/5A2F-89BA/program/IdeaProjects/my_projects/FileMover/src/main/resources/darculafx/darcula.css") );
        scene.getStylesheets().add( fontSheet );

        buttonStart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (pathIn.getText() != null && pathIn.getText().length() > 0) {
                    MoverService moverService = new MoverService();
                    moverService.traverseListSize(textPathIn.getText(), textPathOut.getText());
                    progressBar.setProgress(moverService.traverseList(textPathIn.getText(), textPathOut.getText()));
                    //moverService.traverseList(textPathIn.getText(), textPathOut.getText());
                } else if (pathIn.getText().equals(pathOut.getText()) ||
                        pathIn.getText() != null && pathIn.getText().length() > 0 && pathOut == null ||
                        pathIn.getText() != null && pathIn.getText().length() > 0 && pathOut.getText().length() == 0) {
                    MoverService moverService = new MoverService();
                    moverService.traverseListSize(textPathIn.getText(), textPathIn.getText());
                    progressBar.setProgress(moverService.traverseList(textPathIn.getText(), textPathIn.getText()));
                    //moverService.traverseList(textPathIn.getText(), textPathOut.getText());
                }
            }
        });
        stage.show();

        MenuButton lang = new MenuButton(moverService.getProperties("languageSmall"));
        lang.setMinWidth(60);
        gridPane.add(lang, 2, 2);
        for (int i=0; i<moverService.getLanguagesList().length; i++) {
            MenuItem menuItem = new MenuItem(moverService.getLanguagesList()[i]);
            final int u = i;
            menuItem.setOnAction(e -> moverService.renameTitles(
                    stage, pathIn,
                    pathOut, start,
                    lang, moverService.getLanguageName(u))
            );
            lang.getItems().add(menuItem);
        }
        moverService.languageOpen("strings_ru.properties");
        moverService.renameTitles(
                stage, pathIn,
                pathOut, start,
                lang, moverService.getLanguageName(1));


    }

    public static void main(String[] args) {
        launch();
    }
}