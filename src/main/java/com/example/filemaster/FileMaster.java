package com.example.filemaster;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class FileMaster extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(FileMaster.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        stage.getIcons().add(new Image(FileMaster.class.getResource("567300e6d49142bd910935d0201d6f98.png").openStream()));
        stage.setTitle("FileMaster");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();

    }
}