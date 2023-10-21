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
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();

//        FTPClient client = new FTPClient();
//
//        FTP ftp = new FTP();
//
//
//        int port = 21;
////        String hostname = "185.27.134.11";
////        String username = "if0_35263036";
////        String password = "8BCLZnYQpb1yLaC";
//
//        String hostname = "127.0.0.1";
//        String username = "admin";
//        String password = "admin";
//
//
//        try {
//
//
//            client.connect(hostname, port);
//
//            int replyCode = client.getReplyCode();
//            if (!FTPReply.isPositiveCompletion(replyCode)) {
//                client.disconnect();
//                System.out.println("Failed to connect to: " + hostname);
//                System.exit(1);
//            }
//
//
//            boolean logedin = client.login(username, password);
//            //showServerReply(client);
//            if (!logedin) {
//                System.out.println("Failed to login");
//            } else {
//                System.out.println("Successfully loged in");
//            }
//            client.isConnected();
//
//            System.out.println(client.printWorkingDirectory());
//
//            FTPFile[] files = client.listFiles("/");
//            for (FTPFile file :
//                    files) {
//                if(file.isDirectory()){
//                    System.out.println("-------------------------");
//                    System.out.println("Dir: " + file.getName());
//                    System.out.println("->");
//                    FTPFile[] files2 = client.listFiles("/" + file.getName());
//                    for (FTPFile file2 :
//                            files2) {
//                        System.out.println(file2.getName());
//                    }
//                    System.out.println("-------------------------");
//                }else{
//
//                System.out.println(file.getName());
//                }
//            }
//
//
//
//
//            System.out.println(client.isConnected());
//
//
//            client.disconnect();
//            System.exit(1);
//
//            System.out.println(client.isConnected());
//
//        }
//        catch (IOException e) {
//            System.out.println("chyba pico - fixnout");
//        }


    }
}