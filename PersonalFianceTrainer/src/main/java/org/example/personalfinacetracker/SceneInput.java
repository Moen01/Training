package org.example.personalfinacetracker;


import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SceneInput {
    private Scene scene;
    private SwitchScene switchScene;
    private innPutDataLogg logg = new innPutDataLogg();

    /**
     * @param switchScene
     * @return scenen hvor brukeren legger til data
     * */

    public SceneInput(SwitchScene switchScene) {
        this.switchScene = switchScene;
        BorderPane borderPane = new BorderPane();
        GridPane gridPane = new GridPane();
        Input innData = new Input(logg);
        innPutDataLogg innDataText = new innPutDataLogg();
        Button btnGoBack = new Button("Tilbake til Hovedmeny");
        btnGoBack.setOnAction(e -> switchScene.switchToSceneForsteSide());

        Button slettLogg = new Button("Slett");
        slettLogg.setOnAction(e -> logg.slettData(""));

        Button leggTil = new Button("Legg Til");
        leggTil.setOnAction(e -> {
            switchScene.switchToSceneDisplayMedData(logg);

            String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
            String fileName = "loggData_" + timeStamp + ".txt";

            writeDataToFile(fileName, logg.getLogg());
        });

        gridPane.add(logg.getLoggPane(), 0, 0);
        gridPane.add(slettLogg, 0, 1);
        gridPane.add(leggTil, 0, 2);

        borderPane.setCenter(innData.lagGridPane());
        borderPane.setRight(gridPane);
        borderPane.setBottom(btnGoBack);

        scene = new Scene(borderPane, 700, 500);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
    }

    public Scene getScene() {
        return scene;
    }

    /**
     * @param fileName
     * @param loggData
     *
     * @return skriver ut gitt input til et gitt fil, har flere debug statement her pga
     * feilsøking.
     *
     * */
    private void writeDataToFile(String fileName, String loggData) {
        File directory = new File("files");
        if (!directory.exists()) {
            directory.mkdir();
        }

        File file = new File(directory, fileName);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            System.out.println("Attempting to write to file: " + file.getAbsolutePath());
            writer.write(loggData);
            writer.flush();
            System.out.println("Data successfully written to " + file.getAbsolutePath());
        } catch (IOException ex) {
            System.out.println("Error writing to file: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}