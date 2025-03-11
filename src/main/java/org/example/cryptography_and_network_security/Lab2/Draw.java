package org.example.cryptography_and_network_security.Lab2;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.cryptography_and_network_security.Lab1.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Collectors;

public class Draw {
    char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    // Method to append text to a file
    public static void WriteToFile(String text) {
        try (FileWriter writer = new FileWriter("data.txt", true)) {
            writer.write(text + "\n");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
    // Method to read from a file
    public static String ReadFile() {
        try {
//            return new String(Files.readAllBytes(new File("data.txt").toPath()));
              return Files.lines(new File("data.txt").toPath())
                    // Filter lines containing "Encrypted Text:"
                    .filter(line -> line.contains("Encrypted Text:"))
                    // Remove the "Encrypted Text:" prefix from each line
                    .map(line -> line.replace("Encrypted Text:", "").trim())
                    // Collect all filtered lines into a single string
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

        TextArea DecriptionText = new TextArea();
        DecriptionText.setEditable(false);
        DecriptionText.setPrefRowCount(5);
        DecriptionText.setPrefColumnCount(20);

        TextArea outputAreaPlayfair = new TextArea();
        outputArea.setEditable(false);
        outputArea.setPrefRowCount(10);
        outputArea.setPrefColumnCount(40);

        //Buttons
        Button showFileContentButton = new Button("Show File Content");
        Button PlayfairEncryptButton = new Button("Play Fair Encryption");
        Button PlayfairDecryptButton = new Button("Play Fair Decryption");

        //Actions
        PlayfairEncryptButton.setOnAction(event -> {
            DecriptionText.setText("");
            String plainText = plainTextField.getText();
            if (plainText.isEmpty()) {
                DecriptionText.setText("Plaintext cannot be empty. Please input the plaintext.");
                System.out.println("Error: Plaintext cannot be empty!");
                return;
            }
            String keyInput = keyField.getText();
            if (keyInput.isEmpty()) {
                DecriptionText.setText("Key cannot be empty. Please input the key.");
                System.out.println("Error: Key cannot be empty!");
                return;
            }
            try {
                PlayfairKey playfairKey = new PlayfairKey(keyInput);
                String encryptedText = playfairKey.Encript(plainText);
                outputArea.setText("Encrypted Text: " + encryptedText);
                outputAreaPlayfair.setText( encryptedText);
                // Write the encrypted text and the matrix to the file
                StringBuilder matrixString = new StringBuilder();
                for (int i = 0; i < playfairKey.Matrix_Size; i++) {
                    for (int j = 0; j < playfairKey.Matrix_Size; j++) {
                        matrixString.append(playfairKey.KeyMatrix[i][j]).append(" ");
                    }
                    matrixString.append("\n");
                }
                // Write to file with both encrypted text and the matrix
                WriteToFile("Encrypted Text: " + encryptedText
//                        + "\nKey Matrix:\n" + matrixString.toString() +
//                        "The key used: " + keyInput
                        );
            } catch (IllegalArgumentException e) {
                DecriptionText.setText("Invalid key or plaintext: " + e.getMessage());
                System.out.println("Error during encryption: " + e.getMessage());
            } catch (Exception e) {
                DecriptionText.setText("An error occurred during encryption: " + e.getMessage());
                e.printStackTrace();
                System.out.println("An unexpected error occurred during encryption.");
            }
        });
        PlayfairDecryptButton.setOnAction(event -> {
            DecriptionText.setText("");
            String plainText = plainTextField.getText();
            if (plainText.isEmpty()) {
                DecriptionText.setText("Plaintext cannot be empty. Please input the plaintext.");
                System.out.println("Error: Plaintext cannot be empty!");
                return;
            }
            String keyInput = keyField.getText();
            if (keyInput.isEmpty()) {
                DecriptionText.setText("Key cannot be empty. Please input the key.");
                System.out.println("Error: Key cannot be empty!");
                return;
            }
            try {
                PlayfairKey playfairKey = new PlayfairKey(keyInput);
                String encryptedText = playfairKey.Encript(plainText);
                outputArea.setText("Encrypted Text: " + encryptedText);
                outputAreaPlayfair.setText( encryptedText);
                // Write the encrypted text and the matrix to the file
                StringBuilder matrixString = new StringBuilder();
                for (int i = 0; i < playfairKey.Matrix_Size; i++) {
                    for (int j = 0; j < playfairKey.Matrix_Size; j++) {
                        matrixString.append(playfairKey.KeyMatrix[i][j]).append(" ");
                    }
                    matrixString.append("\n");
                }
                // Write to file with both encrypted text and the matrix
                WriteToFile("Encrypted Text: " + encryptedText
//                        + "\nKey Matrix:\n" + matrixString.toString() +
//                        "The key used: " + keyInput
                );
            } catch (IllegalArgumentException e) {
                DecriptionText.setText("Invalid key or plaintext: " + e.getMessage());
                System.out.println("Error during encryption: " + e.getMessage());
            } catch (Exception e) {
                DecriptionText.setText("An error occurred during encryption: " + e.getMessage());
                e.printStackTrace();
                System.out.println("An unexpected error occurred during encryption.");
            }
        });

//        PlayfairDecryptButton.setOnAction(event -> {
//            DecriptionText.setText(""); // Clear previous messages
//            String cipherText = cipherTextField.getText();
//
//
//            if (cipherText.isEmpty()) {
//                DecriptionText.setText("Ciphertext cannot be empty. Please input the ciphertext.");
//                System.out.println("Error: Ciphertext cannot be empty!");
//                return;
//            }
//            String keyInput = keyField.getText();
//            if (keyInput.isEmpty()) {
//                DecriptionText.setText("Key cannot be empty. Please input the key.");
//                System.out.println("Error: Key cannot be empty!");
//                return;
//            }
//            try {
//                PlayfairKey playfairKey = new PlayfairKey(keyInput);
//                String decryptedText = playfairKey.Dencript(cipherText);
//                WriteToFile("Decrypted Text: " + decryptedText);
//            } catch (IllegalArgumentException e) {
//                DecriptionText.setText("Invalid key or ciphertext: " + e.getMessage());
//                System.out.println("Error during decryption: " + e.getMessage());
//            } catch (Exception e) {
//                DecriptionText.setText("An error occurred during decryption: " + e.getMessage());
//                e.printStackTrace();
//                System.out.println("An unexpected error occurred during decryption.");
//            }
//        });
//
//
//
//
//
//

        PlayfairDecryptButton.setOnAction(event -> {
            DecriptionText.setText("");
            String cipherText = "";
            cipherText = outputAreaPlayfair.getText().trim();
//            try {
//                cipherText = new String(Files.readAllBytes(Paths.get("data.txt")), StandardCharsets.UTF_8).trim();
//
//            } catch (IOException e) {
//                DecriptionText.setText("Error reading ciphertext from file: " + e.getMessage());
//                System.out.println("Error reading ciphertext from file: " + e.getMessage());
//                return;
//            }
            if (cipherText.isEmpty()) {
                DecriptionText.setText("Ciphertext in the file cannot be empty.");
                System.out.println("Error: Ciphertext in the file cannot be empty!");
                return;
            }
            String keyInput = keyField.getText();
            if (keyInput.isEmpty()) {
                DecriptionText.setText("Key cannot be empty. Please input the key.");
                System.out.println("Error: Key cannot be empty!");
                return;
            }
            try {
                PlayfairKey playfairKey = new PlayfairKey(keyInput);
                String decryptedText = playfairKey.Dencript(cipherText);
                outputArea.setText("Decrypted Text: " + decryptedText);
                WriteToFile("Decrypted Text: " + decryptedText);
            } catch (IllegalArgumentException e) {
                DecriptionText.setText("Invalid key or ciphertext: " + e.getMessage());
                System.out.println("Error during decryption: " + e.getMessage());
            } catch (Exception e) {
                DecriptionText.setText("An error occurred during decryption: " + e.getMessage());
                e.printStackTrace();
                System.out.println("An unexpected error occurred during decryption.");
            }
        });


        pane.getChildren().addAll(
                plainTextLabel,
                plainTextField,
                keyLabel,
                keyField,
                outputArea,
                DecriptionText,
                outputAreaPlayfair,
                PlayfairEncryptButton,
                PlayfairDecryptButton,
                showFileContentButton
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
