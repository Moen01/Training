package org.example.personalfinacetracker;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.GridPane;

public class Input {
    private innPutDataLogg logg;

    public Input(innPutDataLogg logg) {
        this.logg = logg;
    }

    /**
     * Dette er skelettet til gridpanet i SceneInput, laget i rader der rad 1=label, rad 2=mat, rad 3=transport,
     * rad 4=underholdning, rad 5=annet.
     * @return(gridpane)
     * */
    public GridPane lagGridPane() {
        Label text = new Label();
        Button knapp = new Button();
        TextField innTekst = new TextField();
        int ascii = 43;
        String sign = Character.toString((char) ascii);

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        // Rad 1
        Label hovedLabel = new Label("Legg til Utgift");
        gridPane.add(hovedLabel, 0, 0);

        // Rad 2 - Mat
        Label textMat = new Label("Mat:");
        TextField innDataMat = createNumberTextField();
        Button knappMat = new Button(sign);

        gridPane.add(textMat, 0, 1);
        gridPane.add(innDataMat, 1, 1);
        gridPane.add(knappMat, 2, 1);

        knappMat.setOnAction(e -> {
            if (validateInput(innDataMat, knappMat)) {
                logg.leggTilData("Mat: " + innDataMat.getText());
                innDataMat.clear();
            }
        });

        // Rad 3 - Transport
        Label textTransport = new Label("Transport:");
        TextField innDataTransport = createNumberTextField();
        Button knappTransport = new Button(sign);

        gridPane.add(textTransport, 0, 2);
        gridPane.add(innDataTransport, 1, 2);
        gridPane.add(knappTransport, 2, 2);

        knappTransport.setOnAction(e -> {
            if (validateInput(innDataTransport, knappTransport)) {
                logg.leggTilData("Transport: " + innDataTransport.getText());
                innDataTransport.clear();
            }
        });

        // Rad 4 - Underholdning
        Label textUnderholdning = new Label("Underholdning:");
        TextField innDataUnderholdning = createNumberTextField();
        Button knappUnderholdning = new Button(sign);

        gridPane.add(textUnderholdning, 0, 3);
        gridPane.add(innDataUnderholdning, 1, 3);
        gridPane.add(knappUnderholdning, 2, 3);

        knappUnderholdning.setOnAction(e -> {
            if (validateInput(innDataUnderholdning, knappUnderholdning)) {
                logg.leggTilData("Underholdning: " + innDataUnderholdning.getText());
                innDataUnderholdning.clear();
            }
        });

        // Rad 5 - Annet
        Label textAnnet = new Label("Annet:");
        TextField innAnnet = createNumberTextField();
        Button knappAnnet = new Button(sign);

        gridPane.add(textAnnet, 0, 4);
        gridPane.add(innAnnet, 1, 4);
        gridPane.add(knappAnnet, 2, 4);

        knappAnnet.setOnAction(e -> {
            if (validateInput(innAnnet, knappAnnet)) {
                logg.leggTilData("Annet: " + innAnnet.getText());
                innAnnet.clear();
            }
        });

        return gridPane;
    }

    /**
     *
     * @return(gir tekstfelt som kun akksepterer tall(int)  )
     * */
    private TextField createNumberTextField() {
        TextField textField = new TextField();
        textField.setTextFormatter(new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*\\.?\\d*")) {
                return change;
            }
            return null;
        }));
        return textField;
    }

    /**
     * @param(TextField textField, Button button)
     * @return(returnerer boolean false= er input ikke gyldig, true= input er gyldig )
     * */
    private boolean validateInput(TextField textField, Button button) {
        String input = textField.getText();
        if (input.isEmpty() || !input.matches("\\d+\\.?\\d*")) {
            return false;
        }
        return true;
    }
}