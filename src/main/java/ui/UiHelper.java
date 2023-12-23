package ui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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


    public GridPane createGridPane(
            GridPane grid,
            int gap) {
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setHgap(gap);
        grid.setVgap(gap);
        return grid;
    }

    public Button createButton(Button button,
                              int width ,
                               int height) {
        button.setMinWidth(width);
        button.setMinHeight(height);
        return button;
    }

    public VBox createVBox(VBox vBox,
                           int width,
                           int height) {
        vBox.setAlignment(Pos.BOTTOM_CENTER);
        vBox.setPrefSize(width, height);
        return vBox;
    }

}
