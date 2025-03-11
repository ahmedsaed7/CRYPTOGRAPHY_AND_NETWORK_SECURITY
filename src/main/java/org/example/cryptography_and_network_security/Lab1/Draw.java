package org.example.cryptography_and_network_security.Lab1;

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
import org.example.cryptography_and_network_security.Lab2.PlayfairKey;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Collectors;

public class Draw  {
    char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    // Method to append text to a file
    public static void WriteToFile(String text) {
        try (FileWriter writer = new FileWriter("data.txt", true)) {
            writer.write(text + "\n");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
    public static void WriteToFile(String filename, String text) {
        try (FileWriter writer = new FileWriter(filename, true)) {
            writer.write(text + "\n");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
    public static String ReadFile() {
        try {
//            return new String(Files.readAllBytes(new File("data.txt").toPath()));
              return Files.lines(new File("data.txt").toPath())
                    .filter(line -> line.contains("Encrypted Text:"))
                    .map(line -> line.replace("Encrypted Text:", "").trim())
                    .collect(Collectors.joining("\n"));
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
            return "";
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

        // Output area
        TextArea outputArea = new TextArea();
        outputArea.setEditable(false);
        outputArea.setPrefRowCount(10);
        outputArea.setPrefColumnCount(40);
        outputArea.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px; -fx-text-fill: #3636a6; -fx-background-color: #f0f0f0;"); // Black text on light gray background

        TextArea DecriptionText = new TextArea();
        DecriptionText.setEditable(false);
        DecriptionText.setPrefRowCount(5);
        DecriptionText.setPrefColumnCount(20);
        DecriptionText.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px; -fx-text-fill: #FF0000; -fx-background-color: #ffffff;"); // Red text on white background
        TextArea outputAreaPlayfair = new TextArea();
        outputArea.setEditable(false);
        outputArea.setPrefRowCount(10);
        outputArea.setPrefColumnCount(40);
        outputAreaPlayfair.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px; -fx-text-fill: #000000; -fx-background-color: #f0f0f0;"); // Black text on light gray background

        //Buttons
        Button encryptButton = new Button("Encrypt"); //background red
        Button decryptButton = new Button("Decrypt"); //  background green
        Button bruteForceButton = new Button("Brute Force Attack"); //
        Button MonoalphabeticDecryptButton = new Button("Monoalphabetic Decrypt");
        Button monoalphabeticEncryptButton = new Button("Monoalphabetic Encrypt");
        Button showFileContentButton = new Button("Show File Content");

        // Set button background colors directly
        encryptButton.setStyle("-fx-background-color: #FF5733; -fx-text-fill: white;"); // Red
        decryptButton.setStyle("-fx-background-color: #28A745; -fx-text-fill: white;"); // Green
        bruteForceButton.setStyle("-fx-background-color: #007BFF; -fx-text-fill: white;"); // Blue
        MonoalphabeticDecryptButton.setStyle("-fx-background-color: #FFC107; -fx-text-fill: black;"); // Yellow
        monoalphabeticEncryptButton.setStyle("-fx-background-color: #6F42C1; -fx-text-fill: white;"); // Purple
        showFileContentButton.setStyle("-fx-background-color: #17A2B8; -fx-text-fill: white;"); // Cyan

        // Arrange buttons in HBoxes
        HBox encryptDecryptBox = new HBox(10, encryptButton, decryptButton);
        encryptDecryptBox.setAlignment(Pos.CENTER);
        HBox bruteForceBox = new HBox(bruteForceButton);
        bruteForceBox.setAlignment(Pos.CENTER);
        HBox monoalphabeticBox = new HBox(10, MonoalphabeticDecryptButton, monoalphabeticEncryptButton);
        monoalphabeticBox.setAlignment(Pos.CENTER);
        HBox showFileContentBox = new HBox(showFileContentButton);
        showFileContentBox.setAlignment(Pos.CENTER);

        //Actions
        encryptButton.setOnAction(event -> {
            DecriptionText.setText("");
            String plainText = plainTextField.getText();
            if (plainText.isEmpty()) {
                DecriptionText.setText("Plaintext cannot be empty please input the plain text");
                System.out.println("ErrorPlaintext cannot be empty ??!" );
                return;
            }
            if (isNumeric(plainText)) {
                DecriptionText.setText("Plaintext cannot be a number. Please input valid text.");
                System.out.println("Error Plaintext cannot be a number??!" );
                return;
            }
            String KeyInput = keyField.getText();
            if (KeyInput.isEmpty()) {
                DecriptionText.setText("The Key Input cannot be empty please input the Key Number");
                System.out.println("Error Key Input cannot be a empty??!" );
                return ;
            }
            if (!isNumeric(KeyInput)) {
                DecriptionText.setText("Key must be a valid integer. Please enter a number between 0 and 25.");
                System.out.println("Error: Key must be a valid integer!");
                return;
            }
            int  key = Integer.parseInt(KeyInput);
            try {
                if (key < 0 || key > 25) {
                    DecriptionText.setText("Key must be between 0 and 25.");
                    System.out.println("Error the key is:- " + key + "\nKey must be between 0 and 25??!" );
                    return;
                }
            }catch (NumberFormatException e) {
                DecriptionText.setText("Invalid key. Please enter a valid integer between 0 and 25.");
                System.out.println("Error Invalid key!!" );
                return;
            }
            try {
                String EncryptionText = Encryption_Cipher.ENCRYPTION(plainText,key);
                outputArea.setText(EncryptionText);
                WriteToFile( "cifher.txt",EncryptionText);
            }catch (Exception e){
                DecriptionText.setText("\"An error occurred during encryption: \" + e.getMessage()");
                 e.printStackTrace();
                System.out.println("An error occurred during encryption");
            }
        });
        decryptButton.setOnAction(event -> {
            String cipherText = ReadFile("cifher.txt");
            String KeyInput = keyField.getText();
            if (KeyInput.isEmpty()) {
                DecriptionText.setText("The Key Input cannot be empty please input the Key Number");
                System.out.println("Error Key Input cannot be a empty??!" );
                return ;
            }
            if (!isNumeric(KeyInput)) {
                DecriptionText.setText("Key must be a valid integer. Please enter a number between 0 and 25.");
                System.out.println("Error: Key must be a valid integer!");
                return;
            }
            int  key = Integer.parseInt(KeyInput);
            try {
                if (key < 0 || key > 25) {
                    DecriptionText.setText("Key must be between 0 and 25.");
                    System.out.println("Error the key is:- " + key + "\nKey must be between 0 and 25??!" );
                    return;
                }
            }catch (NumberFormatException e) {
                DecriptionText.setText("Invalid key. Please enter a valid integer between 0 and 25.");
                System.out.println("Error Invalid key!!" );
                return;
            }
            String DecryptionText = Decryption_Cipher.DECRYPTION(cipherText,key);
            outputArea.setText(DecryptionText);
            WriteToFile("cifher.txt", DecryptionText);
        });
        bruteForceButton.setOnAction(event -> {
            String cipherText = ReadFile("cifher.txt");
            if (cipherText.isEmpty()) {
                outputArea.setText("No Encrypted Text Found in the fill");
                System.out.println("Error No Encrypted Text Found in the fill Chick the file ");
            }
            String bruteForceResult = BruteForceDecryption.BRUTEFORCE(cipherText);
            outputArea.setText(bruteForceResult);
            WriteToFile("data.txt",bruteForceResult);


        });
        monoalphabeticEncryptButton.setOnAction(event -> {
            DecriptionText.setText("");
            String plainText = plainTextField.getText();
            if (plainText.isEmpty()) {
                DecriptionText.setText("Plaintext cannot be empty. Please input the plaintext.");
                System.out.println("Error: Plaintext cannot be empty!");
                return;
            }
            String keyInput = ReadFile("randomkey.txt").replace("\n", "");
            System.out.println(keyInput);
//            WriteToFile(keyInput);
            if (keyInput.isEmpty()) {
                DecriptionText.setText("Key cannot be empty. Please input the key.");
                System.out.println("Error: Key cannot be empty!");
                return;
            }
            try {
                String EncryptionText = MonoalphabeticCipherEncrypt.MONOALPHABETICCIPHERENCRYPTION(plainText , keyInput);
                outputArea.setText("Encrypted Text: " + EncryptionText);
                WriteToFile("MonoalphabeticCipher.txt " , EncryptionText);
            } catch (Exception e){
                DecriptionText.setText("\"An error occurred during encryption: \" + e.getMessage()");
                e.printStackTrace();
                System.out.println("An error occurred during encryption");
            }
        });
        MonoalphabeticDecryptButton.setOnAction(event -> {
            DecriptionText.setText("");
            String plainText = plainTextField.getText();
            if (plainText.isEmpty()) {
                DecriptionText.setText("Plaintext cannot be empty. Please input the plaintext.");
                System.out.println("Error: Plaintext cannot be empty!");
                return;
            }
            String KeyInput = ReadFile("randomkey.txt").replace("\n", "");
            if (KeyInput.isEmpty()) {
                DecriptionText.setText("Key cannot be empty. Please input the key.");
                System.out.println("Error: Key cannot be empty!");
                return;
            }
            try {
                String DencryptionText = MonoalphabeticCipherDecrypt.MONOALPHABETICCIPHERDECRYPTION(plainText , KeyInput);
                outputArea.setText("Dencrypted Text: " + DencryptionText);
                WriteToFile("Encrypted Text: " + DencryptionText);
            } catch (Exception e){
                DecriptionText.setText("\"An error occurred during encryption: \" + e.getMessage()");
                e.printStackTrace();
                System.out.println("An error occurred during encryption");
            }
        });

        pane.getChildren().addAll(
                plainTextLabel,
                plainTextField,
                keyLabel,
                keyField,
                outputArea,
                DecriptionText,
                encryptDecryptBox,
                bruteForceBox,
                monoalphabeticBox,
                showFileContentBox
        );
        Scene scene = new Scene(pane, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Caesar Cipher Tool");
        primaryStage.show();
    }

    private boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
