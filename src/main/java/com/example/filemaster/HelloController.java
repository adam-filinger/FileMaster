package com.example.filemaster;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class HelloController {

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;
    @FXML
    private TextField ip_address;
    @FXML
    private Button disconnect;
    @FXML
    private Button connect;
    @FXML
    private Button download_button;
    @FXML
    private Button upload_button;
    @FXML
    private Button open_dir_button;
    @FXML
    private Button previous_dir_button;
    @FXML
    private TextArea text_area;
    @FXML
    private ListView list_view;
    @FXML
    private Label ftp_file_disp;

    protected static String address;
    protected static String UNAME;
    protected static String PWD;
    protected static FTPFile[] files;

    FTPClient ftp = new FTPClient();

    public HelloController() {
    }


    @FXML
    protected void onConnect() {
        String username_text = username.getText();
        String password_text = password.getText();
        String host = ip_address.getText();

        if(host.isEmpty()){
            text_area.appendText("No host, please type or select host to connect to! \n");
            return;
        }

        try {
            ftp.connect(host, 21);

            int replyCode = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(replyCode)) {
                ftp.disconnect();
                System.out.println("Failed to connect to: " + host);
                System.exit(1);
            }

            text_area.setText("Connected to: " + host + "\n");
            connect.setDisable(true);
            upload_button.setDisable(false);

            boolean logedin = ftp.login(username_text, password_text);

            if (!logedin) {
                text_area.appendText("Login failed!\n");
                System.out.println("Failed to login");
            } else {
                text_area.appendText("Successfully logedin as: " + username_text + "\n");
                System.out.println("Successfully logedin");
            }

            ftp.isConnected();
            disconnect.setVisible(true);


            System.out.println(ftp.printWorkingDirectory());

            show_files();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    @FXML
    protected void show_files(){
        try {
            files = ftp.listFiles(ftp.printWorkingDirectory());
            for (FTPFile file :
                    files) {
                if(file.isDirectory()){
                    if(file.getName().equals("..")){
                        continue;
                    }
                    if(file.getName().equals(".")){
                        continue;
                    }
                    list_view.getItems().add(file.getName() + "(dir)");
                }else{
                    list_view.getItems().add(file.getName());
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
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
            connect.setDisable(false);
            download_button.setDisable(true);
            upload_button.setDisable(true);
            open_dir_button.setDisable(true);
            previous_dir_button.setDisable(true);
            list_view.getItems().clear();


            System.out.println("Disconnected");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void select_conn_button() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("select_conn.fxml"));
        Stage select_conn = new Stage();
        Scene scene = new Scene(fxmlLoader.load(), 1000, 500);
        select_conn.getIcons().add(new Image(HelloApplication.class.getResource("567300e6d49142bd910935d0201d6f98.png").openStream()));
        select_conn.setScene(scene);
        select_conn.setTitle("Select your connection");
        select_conn.setHeight(500);
        select_conn.setWidth(500);
        select_conn.show();
        select_conn.setOnHiding(windowEvent -> {
            ip_address.setText(address);
            username.setText(UNAME);
            password.setText(PWD);
        });


    }
    @FXML
    protected void showFileName(){
        FTPFile file = getSelectedFileFromList();
        ftp_file_disp.setText(file.getName());
    }

    protected FTPFile getSelectedFileFromList(){
        int index = list_view.getSelectionModel().getSelectedIndex();
        FTPFile selected_file = files[index + 2];
        if(selected_file.isDirectory()){
            if(open_dir_button.isDisabled()){
                open_dir_button.setDisable(false);
            }
            if(!download_button.isDisabled()){
                download_button.setDisable(true);
            }
        }else{
            if(!open_dir_button.isDisabled()){
                open_dir_button.setDisable(true);
            }
            if(download_button.isDisabled()){
                download_button.setDisable(false);
            }
        }
        return selected_file;
    }

    @FXML
    protected void download_file(){
        try {

            DirectoryChooser chooser = new DirectoryChooser();
            String dest = chooser.showDialog(upload_button.getParent().getScene().getWindow()).toString();
            FTPFile file = getSelectedFileFromList();
            if (file.isDirectory()){
                download_button.setDisable(true);
                ftp_file_disp.setText("Can't download a directory, please select a file");
            }else{
                download_button.setDisable(false);
                OutputStream input = new FileOutputStream(dest + "/" + file.getName());
                if(ftp.retrieveFile(file.getName(), input)){
                    text_area.appendText("File downloaded successfully! \n");
                }else{
                    text_area.appendText("There was a problem with downloading your file, please try again! \n");
                }
                input.close();

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    protected void upload_file(){
        FileChooser chooser = new FileChooser();
        File file = chooser.showOpenDialog(upload_button.getParent().getScene().getWindow());

        try {
            InputStream input = new FileInputStream(file);
            ftp.appendFile(ftp.printWorkingDirectory() + file.getName(), input);
            input.close();
            list_view.getItems().add(file.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    protected void open_dir(){
        try {
            String dir_name = getSelectedFileFromList().getName();
            if(ftp.changeWorkingDirectory(dir_name)){
                list_view.getItems().clear();
                show_files();
                if(previous_dir_button.isDisabled()){
                    previous_dir_button.setDisable(false);
                }
                open_dir_button.setDisable(true);
            }else{
                text_area.appendText("Couldn't change directory, please try again \n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @FXML
    protected void previous_dir(){
        try{
            if(ftp.changeWorkingDirectory("..")){
                if(ftp.printWorkingDirectory().equals("/")){
                    previous_dir_button.setDisable(true);
                }
                list_view.getItems().clear();
                show_files();
            }else{
                text_area.appendText("Couldn't change directory, please try again");
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }


}