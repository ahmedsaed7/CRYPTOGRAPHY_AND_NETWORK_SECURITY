package org.example.cryptography_and_network_security.Lab2;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.cryptography_and_network_security.Lab2.Draw;

public class Lab2 extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        org.example.cryptography_and_network_security.Lab2.Draw draw = new Draw();
        draw.createUI(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
