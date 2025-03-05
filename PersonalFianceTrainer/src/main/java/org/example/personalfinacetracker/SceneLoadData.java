package org.example.personalfinacetracker;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Map;

public class SceneLoadData {
    private Scene scene;
    private SwitchScene switchScene;
    private innPutDataLogg logg;

    /**
     * @param switchScene
     * @param logg
     *
     * @return scene med en knapp hvor den skal hente text lagret og sende denne dataen
     * til SceneDisplay for en visuel representasjon.
     * */
    public SceneLoadData(SwitchScene switchScene, innPutDataLogg logg) {
        this.switchScene = switchScene;
        this.logg = logg;

        Label infoLabel = new Label("Velg en loggfil for å laste data");
        Button loadButton = new Button("Last opp fil");

        loadButton.setOnAction(event -> {
            File latestFile = openFileDialog();

            if (latestFile != null) {
                String loggText = readFileContent(latestFile);
                System.out.println("File Content:\n" + loggText);

                DataHandlerNy dataHandlerNy = new DataHandlerNy(loggText);
                Map<String, Double> data = dataHandlerNy.getDataInn();
                System.out.println("Data for Charts: " + data);

                logg.clearLogg();
                data.forEach((key, value) -> logg.addLogg(key + ": " + value));


                System.out.println("Logg Data: " + logg.toString());


                switchScene.switchToSceneDisplayMedData(logg);

                infoLabel.setText("Laster opp: " + latestFile.getName());
            } else {
                infoLabel.setText("Ingen loggfiler funnet.");
            }
        });

        VBox layout = new VBox(20, infoLabel, loadButton);
        layout.setStyle("-fx-alignment: center; -fx-padding: 20px;");

        scene = new Scene(layout, 800, 500);
    }



    public Scene getScene() {
        return scene;
    }

    /**
     * @return Her åpner fil for å lese den samt sjekke at den eksisterer.
     *
     * her ble det brukt AI for å få råd til fuknsjoner ved FileChooser klassen.
     *
     * */
    private File openFileDialog() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Velg loggfil");
        File initialDir = new File("files");
        if (initialDir.exists() && initialDir.isDirectory()) {
            fileChooser.setInitialDirectory(initialDir);
        }
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Text Files", "*.txt")
        );
        return fileChooser.showOpenDialog(new Stage());
    }

   /**
    * @param file
    *
    * @return content.toString = en stringbulder for å gjøre den lesbar og om til en string.
    * */
    private String readFileContent(File file) {
        StringBuilder content = new StringBuilder();
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }
            } catch (Exception e) {
                System.out.println("Error reading file: " + e.getMessage());
            }
        }
        return content.toString();
    }
}