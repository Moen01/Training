package org.example.personalfinacetracker;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        SwitchScene switchScene = new SwitchScene(primaryStage);

        SceneForsteSide firstScene = new SceneForsteSide(switchScene);
        primaryStage.setScene(firstScene.getScene());
        primaryStage.setTitle("PersonalFinansLogger");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
