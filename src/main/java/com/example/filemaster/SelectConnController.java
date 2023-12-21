package com.example.filemaster;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SelectConnController  {

    protected static int done = 0;
    public Button select_conn_load_button;

    @FXML
    private ListView select_conn_list;

    @FXML
    private Button select_conn_sel_button;

    @FXML
    private Button select_conn_edit_button;

    @FXML
    private Button select_conn_create_button;

    ArrayList<List<String>> records = new ArrayList<>();

    public SelectConnController(){

        records = MainController.records;

    }


    @FXML
    protected void select_conn(){
        int selectedIndex = select_conn_list.getSelectionModel().getSelectedIndex();
        List<String> selectedHost = records.get(selectedIndex);
        MainController.address = selectedHost.get(1);
        MainController.UNAME = selectedHost.get(2);
        MainController.PWD = selectedHost.get(3);
        Stage stage =(Stage)select_conn_list.getScene().getWindow();
        done = 1;
        stage.close();


    }


    @FXML
    protected void populate_list() {
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



    }
