package com.example.filemaster;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class SelectConnController implements Initializable {

    @FXML
    private ListView select_conn_list;

    ArrayList<List<String>> records = new ArrayList<>();


    public SelectConnController(){

    }


    @FXML
    protected void select_conn(){
        int selectedIndex = select_conn_list.getSelectionModel().getSelectedIndex();
        List<String> selectedHost = records.get(selectedIndex);
        System.out.println(selectedHost);
        MainController.address = selectedHost.get(1);
        MainController.UNAME = selectedHost.get(2);
        MainController.PWD = selectedHost.get(3);
        MainController.is_conn_selected = true;
        Stage stage = (Stage)select_conn_list.getScene().getWindow();
        stage.close();


    }


    @FXML
    protected void populate_list() {


        try (BufferedReader br = new BufferedReader(new FileReader(
                "src/main/resources/com/example/filemaster/ftp_conn_saved.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                records.add(Arrays.asList(values));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < records.size(); i++){
            select_conn_list.getItems().addAll(records.get(i).get(0));
        }
        }

    @FXML
    protected void new_conn() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(FileMaster.class.getResource("manage_saved_conn.fxml"));

        Stage select_conn = new Stage();
        Scene scene = new Scene(fxmlLoader.load(), 1000, 500);
        select_conn.getIcons().add
                (new Image(Objects.requireNonNull(FileMaster.class.
                        getResource("567300e6d49142bd910935d0201d6f98.png")).openStream()));
        select_conn.setScene(scene);
        select_conn.setTitle("Create new connection");
        select_conn.setHeight(500);
        select_conn.setWidth(500);
        select_conn.show();


    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populate_list();
    }
}
