package org.example.personalfinacetracker;

import javafx.stage.Stage;

public class SwitchScene {
    private Stage stage;
    /**
     * @param stage
     *
     * @return inneholder alle switchScene metodene for  å hoppe mellom sidene
     * */
    public SwitchScene(Stage stage) {
        this.stage = stage;
    }

    /**
     * @return sender deg til FørsteSide
     * */
    public void switchToSceneForsteSide(){
        SceneForsteSide sceneForsteSide = new SceneForsteSide(this);
        stage.setScene(sceneForsteSide.getScene());
    }
    /**
     * @return sender deg til Input siden
     * */
    public void switchToSceneInput() {
        SceneInput sceneInput = new SceneInput(this);
        stage.setScene(sceneInput.getScene());
    }
    /**
     * @return sender deg til SceneDisplay hvor det gjøres grafiske representasjonener
     * */
    public void switchToSceneDisplayMedData(innPutDataLogg logg) {
        SceneDisplay sceneDisplay = new SceneDisplay(this, logg);
        stage.setScene(sceneDisplay.getScene());
        System.out.println("Logg Data: " + logg.toString());
    }

    /**
     * @return sender deg til SceneLoadData hvor data hentes inn fra lagret txt filer.
     * */
    public void switchToSceneLoadData() {
        SceneLoadData sceneLoadData = new SceneLoadData(this, new innPutDataLogg());
        stage.setScene(sceneLoadData.getScene());
    }



}
