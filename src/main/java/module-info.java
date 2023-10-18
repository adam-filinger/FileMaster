module com.example.filemaster {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.filemaster to javafx.fxml;
    exports com.example.filemaster;
}