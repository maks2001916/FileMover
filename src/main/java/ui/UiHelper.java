package ui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UiHelper {
    public Stage createStage(Stage stage,
                             String windowName,
                             int width,
                             int height,
                             boolean resizable) {
        stage.setTitle(windowName);
        stage.setWidth(width);
        stage.setHeight(height);
        stage.setResizable(resizable);
        return stage;
    }

    public Scene createScene(VBox vBox,
                             int width,
                             int height) {
        return new Scene(vBox, width, height);
    }


    public void createGridPane(
            GridPane grid,
            int gap) {
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setHgap(gap);
        grid.setVgap(gap);
    }

    public void createButton(Button button,
                             int width ,
                             int height) {
        button.setMinWidth(width);
        button.setMinHeight(height);
    }

    public void createProgressBar(ProgressBar  progressBar,
                                  int width) {
        progressBar.setMinWidth(width);
    }

    public void createVBox(VBox vBox,
                           int width,
                           int height) {
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.setPrefSize(width, height);
    }

    public void createButton(Button button,
                             int width) {
        button.setMinWidth(width);

    }

    public void createMenuButton(MenuButton button,
                             int width) {
        button.setMinWidth(width);

    }

}
