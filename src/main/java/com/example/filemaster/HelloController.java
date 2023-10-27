package com.example.filemaster;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;
import java.util.ArrayList;

public class HelloController {

    @FXML
    private VBox vBox;
    @FXML
    private TextField username;

    @FXML
    private PasswordField password;
    @FXML
    private TextField ip_address;
    @FXML
    private Button disconnect;
    @FXML
    private TextArea text_area;
    @FXML
    private ListView list_view;
    @FXML
    private Label ftp_file_disp;
    @FXML
    private Popup select_conn;

    FTPClient ftp = new FTPClient();

    public HelloController() {
    }


    @FXML
    protected void onConnect() {


        String username_text = username.getText();
        String password_text = password.getText();
        String host = ip_address.getText();

        try {
            ftp.connect(host, 21);

            int replyCode = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(replyCode)) {
                ftp.disconnect();
                System.out.println("Failed to connect to: " + host);
                System.exit(1);
            }

            text_area.setText("Connected to: " + host + "\n");

            boolean logedin = ftp.login(username_text, password_text);

            if (!logedin) {
                text_area.appendText("Login failed!\n");
                System.out.println("Failed to login");
            } else {
                text_area.appendText("Successfully logedin as: " + username_text + "\n");
                System.out.println("Successfully logedin");
            }

//            OutputStream input = new FileOutputStream(new File("C:\\Users\\Thinkpad\\test"));
//            ftp.retrieveFile("DO NOT UPLOAD FILES HERE", input);
//            input.close();
            ftp.isConnected();
            disconnect.setVisible(true);


            System.out.println(ftp.printWorkingDirectory());

            FTPFile[] files = ftp.listFiles("/");
            ArrayList<String> files_list = new ArrayList<>();
            for (FTPFile file :
                    files) {
                files_list.add(file.getName() + "");
            }
            list_view.getItems().addAll(files_list);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @FXML
    protected void onDisconnect(){
        try {
            ftp.disconnect();

            text_area.clear();
            text_area.setText("Disconnected from: " + ip_address.getText());
            username.clear();
            password.clear();
            ip_address.clear();
            disconnect.setVisible(false);
            list_view.getItems().clear();


            System.out.println("Disconnected");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void select_conn_button() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("select_conn.fxml"));
        Stage select_conn = new Stage();
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        select_conn.getIcons().add(new Image(HelloApplication.class.getResource("567300e6d49142bd910935d0201d6f98.png").openStream()));
        select_conn.setScene(scene);
        select_conn.setTitle("Select your connection");
        select_conn.setHeight(500);
        select_conn.setWidth(500);
        select_conn.show();



    }
    @FXML
    protected void showFileName(){
        String selected_item = list_view.getSelectionModel().getSelectedItem().toString();
        ftp_file_disp.setText(selected_item);
    }
}