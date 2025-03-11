package org.example.cryptography_and_network_security.Lab1;

import javafx.application.Application;
import javafx.stage.Stage;
public class Test  extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        Draw draw = new Draw();
        draw.createUI(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
