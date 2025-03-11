module org.example.cryptography_and_network_security {
    requires javafx.web;
requires java.compiler;
requires java.base;
requires java.security.jgss;
    requires javafx.controls;
    requires javafx.fxml;

    exports org.example.cryptography_and_network_security.Lab1 to javafx.graphics;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
//    requires eu.hansolo.tilesfx;

    opens org.example.cryptography_and_network_security to javafx.fxml;
    exports org.example.cryptography_and_network_security;
    exports org.example.cryptography_and_network_security.Lab2 to javafx.graphics;
    exports org.example.cryptography_and_network_security.Lab3 to javafx.graphics;
}