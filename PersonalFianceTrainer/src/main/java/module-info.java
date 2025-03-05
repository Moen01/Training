module org.example.personalfinacetracker {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens org.example.personalfinacetracker to javafx.fxml;
    exports org.example.personalfinacetracker;
}