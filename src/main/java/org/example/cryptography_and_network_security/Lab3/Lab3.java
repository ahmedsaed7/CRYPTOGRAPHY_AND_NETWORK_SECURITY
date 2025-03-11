package org.example.cryptography_and_network_security.Lab3;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.cryptography_and_network_security.Lab3.Draw;

public class Lab3 extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        Draw draw = new Draw();
        draw.createUI(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}