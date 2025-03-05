package org.example.personalfinacetracker;
import javafx.animation.FadeTransition;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SceneForsteSide {
    private Scene scene;
    private SwitchScene switchScene;
    private innPutDataLogg logg = new innPutDataLogg();

    /**
     * @param switchScene
     *
     * Scenen Brukeren blir møtt med først
     * */
    public SceneForsteSide(SwitchScene switchScene) {
        this.switchScene = switchScene;


        Button btnGoToLoadData = new Button("Gå til lagret data");
        btnGoToLoadData.setOnAction(e -> switchScene.switchToSceneLoadData());
        

        Button btnGoToInput = new Button("Legg til data");
        btnGoToInput.setOnAction(e -> switchScene.switchToSceneInput());


        VBox layout = new VBox(20, btnGoToInput,btnGoToLoadData);
        layout.setStyle("-fx-alignment: center; -fx-padding: 20px;");

        scene = new Scene(layout, 800, 500);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
    }
        
    public Scene getScene() {
        return scene;
    }


}
