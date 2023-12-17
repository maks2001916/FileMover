package service;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;


import java.io.File;

public class HomeWindowAction {

    public HomeWindowAction() {

    }

    public void selectTheDirectory(Stage stage, Button openPathIn, TextField textPathIn, String title) {
        openPathIn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                final DirectoryChooser directoryChooser = new DirectoryChooser();
                directoryChooser.setTitle(title);
                final File selectedDirectory = directoryChooser.showDialog(stage);
                if (selectedDirectory != null) {
                    textPathIn.setText(selectedDirectory.getAbsolutePath());
                }
            }

        });
    }

    public void moving(Button buttonStart,
                       Label pathIn,
                       TextField textPathIn,
                       Label pathOut,
                       TextField textPathOut,
                       ProgressBar progressBar) {
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
    }
}
