package org.example.personalfinacetracker;


import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import java.util.Map;



public class SceneDisplay {
    private Scene scene;
    private innPutDataLogg logg;
    private SwitchScene switchScene;
    private innPutDataLogg dataLogg;
    private DataHandlerNy dataHandlerNy;
    public SceneDisplay(SwitchScene switchScene){}
    /**
     * @param(SwitchScene switchScene, innPutDataLogg logg)
     * @return(scenen for SceneDisplay)
     * */
    public SceneDisplay(SwitchScene switchScene, innPutDataLogg logg) {
        this.switchScene = switchScene;
        this.logg = logg;
       // this.dataHandler = new DataHandler(logg.toString());
        this.dataHandlerNy = new DataHandlerNy(logg.getLogg());

        BorderPane boarderPane = new BorderPane();
        PieChart pieChart = createPieChart();
        

        BarChart<String, Number> barChart = createBarChart();


        VBox chartsLayout = new VBox(20, pieChart, barChart);
        chartsLayout.setAlignment(Pos.CENTER);

        Button btnGoBack = new Button("Tilbake til Hovedmeny");
        btnGoBack.setOnAction(e -> switchScene.switchToSceneForsteSide());

        VBox textPane = new VBox();
        TextField totDisplay = displayTotForbruk();
        textPane.getChildren().add(totDisplay);

        boarderPane.setRight(chartsLayout);
        boarderPane.setCenter(totDisplay);
        boarderPane.setBottom(btnGoBack);

        scene = new Scene(boarderPane, 800, 700);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

    }
    /**
     *
     * @return(textfelt - henter data og summerer dem til en samlet Totalsum)
     * */
    private TextField displayTotForbruk(){
        TextField textFelt = new TextField();
        textFelt.setEditable(false);
        textFelt.setStyle("-fx-font-size: 20px; -fx-text-alignment: center;");

        Map<String, Double> data = dataHandlerNy.getDataInn();
        System.out.println("Data for Charts: " + data);
        double totalt = data.values().stream().mapToDouble(Double::doubleValue).sum();
        animateScrollingText(textFelt);
        textFelt.setText("Totalt forbruk: " + totalt);

        return textFelt;
    }

    /**
     * @param(SwitchScene switchScene, innPutDataLogg logg)
     * @return(scenen for SceneDisplay)
     * */
    private PieChart createPieChart() {
        PieChart pieChart = new PieChart();
        Map<String, Double> data = dataHandlerNy.getDataInn();

        for (Map.Entry<String, Double> entry : data.entrySet()) {
            pieChart.getData().add(new PieChart.Data(entry.getKey(), entry.getValue()));
        }

        return pieChart;
    }

    /**
     * @param(SwitchScene switchScene, innPutDataLogg logg)
     *
     * her har jeg brukt AI for en firståelse av BarChart importen.
     *
     * @return(scenen for SceneDisplay)
     * */
    private BarChart<String, Number> createBarChart() {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        Map<String, Double> data = dataHandlerNy.getDataInn();

        // Define colors for different categories
        String[] colors = {"#db3a00", "#FF9800", "#4CAF50", "#2196F3"};

        int index = 0;
        for (Map.Entry<String, Double> entry : data.entrySet()) {
            XYChart.Data<String, Number> dataPoint = new XYChart.Data<>(entry.getKey(), entry.getValue());
            series.getData().add(dataPoint);
            final int colorIndex = index;
            dataPoint.nodeProperty().addListener((obs, oldNode, newNode) -> {
                if (newNode != null) {
                    newNode.setStyle("-fx-bar-fill: " + colors[colorIndex % colors.length] + ";");
                }
            });
            index++;
        }

        barChart.getData().add(series);
        return barChart;
    }

    /**
     * @param(TextField tekstFelt)
     *
     * her måtte jeg ha help fra chatGTP pga tid sparing.
     *
     * @return(en animasjo som gjør at texstfeltet flasher)
     * */
    private void animateScrollingText(TextField tekstFelt) {
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1), tekstFelt);
        translateTransition.setFromX(0); // Start from the original position
        translateTransition.setToX(5);  // Move 5 pixels to the right
        translateTransition.setAutoReverse(true); // Move back to the original position
        translateTransition.setCycleCount(TranslateTransition.INDEFINITE); // Repeat indefinitely

        // Create a FadeTransition for the flashing effect
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5), tekstFelt);
        fadeTransition.setFromValue(1.0); // Fully opaque
        fadeTransition.setToValue(0.3);   // Partially transparent
        fadeTransition.setAutoReverse(true); // Fade back to fully opaque
        fadeTransition.setCycleCount(FadeTransition.INDEFINITE); // Repeat indefinitely

        // Start both animations
        translateTransition.play();
        fadeTransition.play();
    }


    public Scene getScene() {
        return scene;
    }

}



