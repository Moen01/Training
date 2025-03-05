    package org.example.personalfinacetracker;


    import javafx.scene.control.TextArea;
    import javafx.scene.layout.VBox;
    import java.util.ArrayList;
    import java.util.HashMap;
    import java.util.List;
    import java.util.Map;

    public class innPutDataLogg {

        private List<String> datalog= new ArrayList<>();

        private Map<String, Double> dataLogg = new HashMap<>();
        private TextArea loggFelt;

        public innPutDataLogg(){
            loggFelt = new TextArea();
            loggFelt.setEditable(false);
            loggFelt.setPrefWidth(200);
            loggFelt.setPrefHeight(400);
            oppdaterFelt();
        }
        /**
         * @return(oppdaterer feltet)
         * **/
        public void oppdaterFelt(){
            loggFelt.setText("Logg:\n"+String.join("\n",datalog));
        }

        /**
         * @param(String input fra SceneInput)
         * @return(legger til input inn i datalog)
         * **/
        public void leggTilData(String input){
            datalog.add(input);
            oppdaterFelt();

        }

        /**
         * @param(String input fra SceneInput)
         * @return(String input som slettes)
         * **/
        public void slettData(String input){
            datalog.clear();
            oppdaterFelt();
        }

        /**
         *
         * @return(loggBox for å sette avstand(spacing)
         * **/

        public VBox getLoggPane(){
            VBox loggBox = new VBox(loggFelt);
            loggBox.setSpacing(10);
            return loggBox;

        }

        /**
         * @return(renser loggen tom)
         * **/
        public void clearLogg() {
            dataLogg.clear();
        }

        /**
         * getLogg
         * **/

        public String getLogg() {
            return String.join("\n", datalog);
        }
        /**
         * toString
         * **/

        @Override
        public String toString() {
            return String.join("\n", datalog);
        }
        /**
         * @param(String loggText fra SceneInput
         *
         * @return(prossesering av en mulitilinje loggText hvor den parser input, tar ut relevant data
         * og oppdaterer det.
         * **/
        public void addLogg(String loggText) {

            String[] lines = loggText.split("\n");
            for (String line : lines) {
                String[] deler = line.split(":");
                if (deler.length == 2) {
                    String kategori = deler[0].trim();
                    try {
                        double mengde = Double.parseDouble(deler[1].trim());
                        dataLogg.put(kategori, mengde);
                        datalog.add(kategori + ": " + mengde);
                    } catch (NumberFormatException e) {
                        System.out.println("Feil i tallformat i logg: " + deler[1]);
                    }
                }
            }
            oppdaterFelt();
        }

    }
