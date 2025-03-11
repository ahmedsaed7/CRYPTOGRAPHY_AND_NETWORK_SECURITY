package org.example.cryptography_and_network_security.Lab3;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Collectors;

public class Draw {

    public static void WriteToFile(String filename, String text) {
        try (FileWriter writer = new FileWriter(filename, false)) { // Overwrite mode
            writer.write(text);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public static String ReadFile(String filename) {
        try {
            return Files.lines(new File(filename).toPath())
                    .collect(Collectors.joining("\n"));
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
            return "";
        }
    }

    public void createUI(Stage primaryStage) {
        VBox pane = new VBox(20);
        pane.setPadding(new Insets(20));
        pane.setAlignment(Pos.CENTER);

        // Input Fields
        Label plainTextLabel = new Label("Enter Plaintext:");
        TextField plainTextField = new TextField();
        Label keyLabel = new Label("Enter Key (Shift):");
        TextField keyField = new TextField();

        // Output Area
        TextArea outputArea = new TextArea();
        outputArea.setEditable(false);
        outputArea.setPrefRowCount(10);
        outputArea.setPrefColumnCount(40);
        outputArea.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px; -fx-text-fill: #3636a6; -fx-background-color: #f0f0f0;");

        TextArea DecriptionText = new TextArea();
        DecriptionText.setEditable(false);
        DecriptionText.setPrefRowCount(5);
        DecriptionText.setPrefColumnCount(20);
        DecriptionText.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px; -fx-text-fill: #FF0000; -fx-background-color: #ffffff;");

        // Buttons
        Button Vigenere_Cipher_Encryption = new Button("Vigenere_Cipher_Encryption");
        Button Vigenere_Cipher_Decryption = new Button("Vigenere_Cipher_Decryption");
        Button One_Time_Pad_Encryption = new Button("One_Time_Pad_Encryption");
        Button One_Time_Pad_Decryption = new Button("One_Time_Pad_Decryption");

        // Arrange Buttons in HBoxes
        HBox Vigenere_Cipher_Box = new HBox(10, Vigenere_Cipher_Encryption, Vigenere_Cipher_Decryption);
        Vigenere_Cipher_Box.setAlignment(Pos.CENTER);

        HBox One_Time_Pad_Box = new HBox(10, One_Time_Pad_Encryption, One_Time_Pad_Decryption);
        One_Time_Pad_Box.setAlignment(Pos.CENTER);

        // Set Button Background Colors
        Vigenere_Cipher_Encryption.setStyle("-fx-background-color: #FF5733; -fx-text-fill: white;");
        Vigenere_Cipher_Decryption.setStyle("-fx-background-color: #28A745; -fx-text-fill: white;");
        One_Time_Pad_Encryption.setStyle("-fx-background-color: #c818e3; -fx-text-fill: white;");
        One_Time_Pad_Decryption.setStyle("-fx-background-color: #4560cd; -fx-text-fill: white;");

        // Actions
        Vigenere_Cipher_Encryption.setOnAction(event -> {
            DecriptionText.setText("");
            String plainText = plainTextField.getText();
            if (plainText.isEmpty()) {
                DecriptionText.setText("Plaintext cannot be empty. Please input the plain text.");
                return;
            }
            if (isNumeric(plainText)) {
                DecriptionText.setText("Plaintext cannot be a number. Please input valid text.");
                return;
            }
            String KeyInput = keyField.getText();
            if (KeyInput.isEmpty()) {
                DecriptionText.setText("Key cannot be empty. Please input the key string.");
                return;
            }
            if (!isKeyValid(KeyInput)) {
                DecriptionText.setText("Key must consist of letters only (A-Z). Numbers and symbols are not allowed.");
                return;
            }
            String key = KeyInput.toUpperCase();
            try {
                String Vigenere_EncryptionText = Vigenere_Cipher_Encryption_Decryption.Vigenere_Cipher_Encryption(plainText, key);
                outputArea.setText(Vigenere_EncryptionText);
                WriteToFile("Vigenere_Cipher.txt", Vigenere_EncryptionText);
            } catch (Exception e) {
                DecriptionText.setText("Encryption error: " + e.getMessage());
                e.printStackTrace();
            }
        });
        Vigenere_Cipher_Decryption.setOnAction(event -> {
            DecriptionText.clear();
            String cipherText = outputArea.getText().trim();
            if (cipherText.isEmpty()) {
                DecriptionText.setText("No ciphertext to decrypt. Please encrypt first or provide ciphertext.");
                return;
            }
            String KeyInput = keyField.getText();
            if (KeyInput.isEmpty()) {
                DecriptionText.setText("Key is required for decryption.");
                return;
            }
            if (!isKeyValid(KeyInput)) {
                DecriptionText.setText("Key must consist of letters only (A-Z). Numbers and symbols are not allowed.");
                return;
            }
            String key = KeyInput.toUpperCase();
            try {
                String decryptedText = Vigenere_Cipher_Encryption_Decryption.Vigenere_Cipher_Decryption(cipherText, key);
                outputArea.setText(decryptedText);
                WriteToFile("Vigenere_Decrypted.txt", decryptedText);
            } catch (Exception e) {
                DecriptionText.setText("Decryption error: " + e.getMessage());
                e.printStackTrace();
            }
        });
        One_Time_Pad_Encryption.setOnAction(event -> {
            DecriptionText.setText("");
            String plainText = plainTextField.getText();
            if (plainText.isEmpty()) {
                DecriptionText.setText("Plaintext cannot be empty. Please input the plain text.");
                return;
            }
            if (isNumeric(plainText)) {
                DecriptionText.setText("Plaintext cannot be a number. Please input valid text.");
                return;
            }
            try {
                String[] result = One_Time_Pad.One_Time_Pad_Encryption(plainText);
                String validationResult = One_Time_Pad.validateEncryptionResult(result);
                if (!validationResult.isEmpty()) {
                    DecriptionText.setText(validationResult);
                    return;
                }
                String Ciphertext = result[0];
                String RandomKey = result[1];
                outputArea.setText(Ciphertext);
                WriteToFile("One_Time_par_Cipher_Encryption.txt", Ciphertext);
                WriteToFile("One_Time_par_Random_Key.txt", RandomKey);
            } catch (Exception e) {
                DecriptionText.setText("Encryption error: " + e.getMessage());
                e.printStackTrace();
            }
        });

        One_Time_Pad_Decryption.setOnAction(event -> {
            DecriptionText.setText("");
            String CipherText = ReadFile("One_Time_par_Cipher_Encryption.txt");
            if (CipherText.isEmpty()) {
                DecriptionText.setText("No ciphertext found. Please encrypt first.");
                return;
            }
            String KeyInput = ReadFile("One_Time_par_Random_Key.txt");
            if (KeyInput.isEmpty()) {
                DecriptionText.setText("No key found. Please generate a key first.");
                return;
            }
            if (!isKeyValid(KeyInput)) {
                DecriptionText.setText("Invalid key format. Key must consist of uppercase letters only.");
                return;
            }
            try {
                String decryptedText = One_Time_Pad.One_Time_Pad_Decryption(CipherText, KeyInput);
                outputArea.setText(decryptedText);
                WriteToFile("One_Time_Pad_Decryption.txt", decryptedText);
            } catch (Exception e) {
                DecriptionText.setText("Decryption error: " + e.getMessage());
                e.printStackTrace();
            }
        });

        pane.getChildren().addAll(
                plainTextLabel,
                plainTextField,
                keyLabel,
                keyField,
                outputArea,
                DecriptionText,
                Vigenere_Cipher_Box,
                One_Time_Pad_Box
        );

        Scene scene = new Scene(pane, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Cryptography Tool");
        primaryStage.show();
    }

    private boolean isKeyValid(String key) {
        return key.matches("^[A-Z]+$"); // Key must consist of uppercase letters only
    }

    private boolean isNumeric(String input) {
        return input.matches("-?\\d+(\\.\\d+)?");
    }
}