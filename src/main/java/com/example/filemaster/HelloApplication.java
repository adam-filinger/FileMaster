package com.example.filemaster;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.commons.net.ftp.*;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        FTPClient client = new FTPClient();

        FTP ftp = new FTP();


        int port = 21;
        String hostname = "127.0.0.1";

        try {
            client.connect(hostname, port);

            int replyCode = client.getReplyCode();
            if (!FTPReply.isPositiveCompletion(replyCode)) {
                client.disconnect();
                System.out.println("Failed to connect to: " + hostname);
                System.exit(1);
            }


            boolean logedin = client.login("admin", "admin");
            //showServerReply(client);
            if (!logedin) {
                System.out.println("Failed to login");
            } else {
                System.out.println("Successfully loged in");
            }
            client.isConnected();


            FTPFile[] files = client.listFiles("/");
            for (FTPFile file :
                    files) {
                System.out.println(file.getName());
            }




            System.out.println(client.isConnected());


            client.disconnect();
            System.exit(1);

            System.out.println(client.isConnected());

        }
        catch (IOException e) {

        }


    }
}