package ui;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import service.HomeWindowAction;
import service.MoverService;
import service.OpenLanguageResources;

import java.io.File;

public class HomeWimdow {

    private UiHelper uiHelper;
    private MoverService moverService;
    private OpenLanguageResources languageResources;
    private Stage stage;
    private String fontSheet;
    private GridPane gridPane;
    private VBox vBox;
    private Scene scene;
    private final int widthStage = 415;
    private final int heigtStage = 160;
    private final int width_ButtonStart = 200;
    private final int height_ButtonStart = 20;
    private final int width_ButtonsOperation = 60;
    private final int width_ProgressBar = 400;
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
    private MenuButton selectLanguage;
    private ProgressBar progressBar;


    public HomeWimdow(Stage inputStage) {
        languageResources = new OpenLanguageResources();

        uiHelper = new UiHelper();
        moverService = new MoverService();
        gridPane = new GridPane();
        vBox = new VBox();
        uiHelper.createVBox(vBox, widthStage, heigtStage);
        pathIn = new Label(moverService.getProperties("pathIn"));
        pathOut = new Label(moverService.getProperties("pathOut"));
        labelStart = new Label(moverService.getProperties("move"));
        textPathIn = new TextField();
        textPathOut = new TextField();
        progressBar = new ProgressBar();
        uiHelper.createProgressBar(progressBar, width_ProgressBar);
        openPathIn = new Button("...");
        uiHelper.createButton(openPathIn, width_ButtonsOperation);
        openPathOut = new Button("...");
        uiHelper.createButton(openPathOut, width_ButtonsOperation);
        buttonStart = new Button("->");
        uiHelper.createButton(buttonStart, width_ButtonStart, height_ButtonStart);
        selectLanguage = new MenuButton(moverService.getProperties("languageSmall"));
        uiHelper.createMenuButton(selectLanguage, width_ButtonsOperation);


        stage = uiHelper.createStage(
                inputStage,
                moverService.getProperties("title"),
                widthStage,
                heigtStage,
                resizable);
    }

    public void start() {
        languageResources.renameTitles(this, 1);
        uiHelper.createGridPane(gridPane, sizeGap);
        createButtonSelectLanguage();
        gridAddElements();
        scene = uiHelper.createScene(vBox, widthStage, heigtStage);
        setFontSheet();
        stage.setScene(scene);
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
        gridPane.add(selectLanguage, 2, 2);
        vBox.getChildren().addAll(gridPane, progressBar);

        activationAction();
    }

    private void createButtonSelectLanguage() {
        //создание меню для выбора языка интерфейса
        for (int i = 0; i<languageResources.getDirectory().length; i++) {
            RadioMenuItem menuItem = new RadioMenuItem(languageResources.getPropertiesLangName()[i]);
            final int u = i;
            menuItem.setOnAction(e -> languageResources.renameTitles(
                    this, u)
            );
            //menuItem.setOnAction(e -> start());
            selectLanguage.getItems().add(menuItem);
        }

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

    private void setFontSheet() {
        fontSheet = moverService.fileToStylesheetString(new File(
                "/run/media/deck/5A2F-89BA/program/IdeaProjects/my_projects/FileMover/src/main/resources/darculafx/darcula.css") );
        scene.getStylesheets().add( fontSheet );
    }

    public Stage getStage() {
        return stage;
    }

    public Label getPathIn() {
        return pathIn;
    }

    public Label getPathOut() {
        return pathOut;
    }

    public Label getLabelStart() {
        return labelStart;
    }

    public MenuButton getSelectLanguage() {
        return selectLanguage;
    }
}
