package ui;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import service.HomeWindowAction;
import service.MoverService;

public class HomeWimdow {

    private UiHelper uiHelper;
    private MoverService moverService;
    private Stage stage;
    private GridPane gridPane;
    private VBox vBox;
    private final int widthStage = 415;
    private final int heigtStage = 160;
    private final int sizeGap = 10;
    private final boolean resizable = true;
    private Label pathIn;
    private Label pathOut;
    private Label labelStart;
    private TextField textPathIn;
    private TextField textPathOut;
    private Button openPathIn;
    private Button openPathOut;
    private Button buttonStart;
    private ProgressBar progressBar;


    public HomeWimdow(Stage inputStage) {
        uiHelper = new UiHelper();
        moverService = new MoverService();
        gridPane = new GridPane();
        vBox = new VBox();
        pathIn = new Label(moverService.getProperties("pathIn"));
        pathOut = new Label(moverService.getProperties("pathOut"));
        labelStart = new Label(moverService.getProperties("move"));
        textPathIn = new TextField();
        textPathOut = new TextField();
        progressBar = new ProgressBar();
        openPathIn = new Button("...");
        openPathOut = new Button("...");
        buttonStart = new Button("->");


        stage = uiHelper.createStage(
                inputStage,
                moverService.getProperties("title"),
                widthStage,
                heigtStage,
                resizable);
    }

    public void start() {
        uiHelper.createGridPane(gridPane, sizeGap);
    }

    private void gridAddElements() {
        gridPane.add(pathIn, 0, 0);
        gridPane.add(textPathIn, 1, 0);
        gridPane.add(openPathIn, 2, 0);

        gridPane.add(pathOut, 0, 1);
        gridPane.add(textPathOut, 1, 1);
        gridPane.add(openPathOut, 2, 1);

        gridPane.add(labelStart, 0, 2);
        gridPane.add(buttonStart, 1, 2);
        vBox.getChildren().addAll(gridPane, progressBar);

        activationAction();
    }

    private void activationAction() {
        HomeWindowAction homeWindowAction = new HomeWindowAction();
        homeWindowAction.selectTheDirectory(
                stage,
                openPathIn,
                textPathIn,
                moverService.getProperties("selectTheSourceDirectory"));
        homeWindowAction.selectTheDirectory(
                stage,
                openPathOut,
                textPathOut,
                moverService.getProperties("selectTheTargetAudience")
        );
        homeWindowAction.moving(buttonStart,
                pathIn,
                textPathIn,
                pathOut,
                textPathOut,
                progressBar);
    }

}
