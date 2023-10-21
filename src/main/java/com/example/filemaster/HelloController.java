package com.example.filemaster;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.IOException;

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
    private TextArea text_area;

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
                text_area.appendText("Successfully loged in as: " + username_text + "\n");
                System.out.println("Successfully loged in");
            }
            ftp.isConnected();
            disconnect.setVisible(true);


            System.out.println(ftp.printWorkingDirectory());

            FTPFile[] files = ftp.listFiles("/");
            String text = "";
            for (FTPFile file :
                    files) {
                text = text + file.getName() + "\n";
            }
                text_area.appendText(text);

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

            System.out.println("Disconnected");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}