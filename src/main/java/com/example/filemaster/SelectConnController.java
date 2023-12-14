package com.example.filemaster;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.util.ArrayList;
import java.util.List;

public class SelectConnController{

    protected static int done = 0;

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
        int selectedIndex = select_conn_list.getSelectionModel().getSelectedIndex() + 1;
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
        for (int i = 1; i < records.size(); i++){
            select_conn_list.getItems().addAll(records.get(i).get(0));
        }
        }



    }
