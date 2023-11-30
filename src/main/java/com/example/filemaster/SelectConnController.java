package com.example.filemaster;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SelectConnController {

    @FXML
    private ListView select_conn_list;

    @FXML
    private Button select_conn_sel_button;

    @FXML
    private Button select_conn_edit_button;

    @FXML
    private Button select_conn_create_button;

    ArrayList<List<String>> records = new ArrayList<>();
    public SelectConnController() throws URISyntaxException {


        try (BufferedReader br = new BufferedReader(new FileReader(new File(FileMaster.class.getResource("ftp_conn_saved.csv").toURI())))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                records.add(Arrays.asList(values));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    protected void select_conn(){
        String host = select_conn_list.getSelectionModel().getSelectedItem().toString();
        for (List<String> temp :
                records) {
            if(temp.contains(host)) {
                MainController.address = temp.get(1);
                System.out.println(temp.get(1));
                MainController.UNAME = temp.get(2);
                System.out.println(temp.get(2));
                MainController.PWD = temp.get(3);
                System.out.println(temp.get(3));
                break;

            }

        }
    }


    @FXML
    protected void populate_list() {
        for (int i = 1; i < records.size(); i++){
            select_conn_list.getItems().addAll(records.get(i).get(0));
        }
        }



    }
