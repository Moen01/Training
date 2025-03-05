package org.example.personalfinacetracker;


import java.util.HashMap;
import java.util.Map;

public class DataHandlerNy {
    private Map<String, Double> dataInn = new HashMap<>();
    private double mat = 0.0;
    private double transport = 0.0;
    private double underholdning = 0.0;
    private double annet = 0.0;

    public DataHandlerNy(String loggText) {
        processLogData(loggText);
    }
/**
 * @param(String loggtext fra SceneInput
 *
 *
 * @return (hva som breaker ut av switchen, data for mar, transport, underholdning og annet)
 * */
    public void processLogData(String loggText) {
        loggText = loggText.replace("Logg Data: ", "")
                .replace("[", "")
                .replace("]", "");

        String[] lines = loggText.split("\\r?\\n");

        for (String line : lines) {
            String[] deler = line.split(":");
            if (deler.length == 2) {
                String kategori = deler[0].trim();
                try {
                    double mengde = Double.parseDouble(deler[1].trim());

                    switch (kategori.toLowerCase()) {
                        case "mat":
                            mat = mengde;
                            break;
                        case "transport":
                            transport = mengde;
                            break;
                        case "underholdning":
                            underholdning = mengde;
                            break;
                        case "annet":
                            annet = mengde;
                            break;
                    }


                    dataInn.put(kategori, mengde);

                    System.out.println("Updated dataInn: " + dataInn);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number format in logg: " + deler[1]);
                }
            }
        }
    }


    // Gettere for spesifikke kategorier
    public double getMat() {
        return mat;
    }

    public double getTransport() {
        return transport;
    }

    public double getUnderholdning() {
        return underholdning;
    }

    public double getAnnet() {
        return annet;
    }

    public Map<String, Double> getDataInn() {
        return dataInn;
    }
}
