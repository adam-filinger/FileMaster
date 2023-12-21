module com.example.filemaster {
    requires javafx.controls;
    requires javafx.fxml;
    requires commons.net;
    requires opencsv;


    opens com.example.filemaster to javafx.fxml;
    exports com.example.filemaster;
}