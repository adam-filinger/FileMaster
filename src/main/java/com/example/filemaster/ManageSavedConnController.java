package com.example.filemaster;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class ManageSavedConnController {

    @FXML
    private TextField address;
    @FXML
    private TextField UNAME;
    @FXML
    private TextField PWD;
    @FXML
    private TextField conn_name;

    @FXML
    private void make(){

        File f = new File("src/main/resources/com/example/filemaster/ftp_conn_saved.csv");

        if(f.exists()){
            System.out.println("exists");
            System.out.println(f.getAbsolutePath());
        }else{
            System.out.println("doesnt exist");
        }

        try{

            FileWriter fw = new FileWriter(f, true);

            CSVWriter writer = new CSVWriter(fw, ';', CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_LINE_END);

            String[] data = {conn_name.getText(), address.getText(), UNAME.getText(), PWD.getText()};


            writer.writeNext(data);
            writer.close();
            System.out.println("saved");



        } catch (IOException e) {
            throw new RuntimeException(e);
        }




    }


}
