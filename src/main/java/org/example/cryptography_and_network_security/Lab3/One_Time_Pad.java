package org.example.cryptography_and_network_security.Lab3;

import java.util.Locale;

public class One_Time_Pad {

    public static String[] One_Time_Pad_Encryption(String Plaintext) {
        Plaintext = HandelPlainText(Plaintext); // Remove spaces
        String RandomKey = generateRandomKey(Plaintext.length());
        System.out.println("Generated Key: " + RandomKey);

        StringBuilder Ciphertext = new StringBuilder();
        int keyIndex = 0;

        for (char c : Plaintext.toCharArray()) {
            if (Character.isLetter(c)) {
                int shift = RandomKey.charAt(keyIndex % RandomKey.length()) - 'A'; // A = 65
                char encryptedChar;
                if (Character.isUpperCase(c)) {
                    encryptedChar = (char) ((c - 'A' + shift) % 26 + 'A');
                } else {
                    encryptedChar = (char) ((c - 'a' + shift) % 26 + 'a'); // a = 97
                }
                Ciphertext.append(encryptedChar);
                keyIndex++;
            } else {
                Ciphertext.append(c);
            }
        }

        return new String[] { Ciphertext.toString(), RandomKey };
    }

    public static String One_Time_Pad_Decryption(String Ciphertext, String RandomKey) {
        if (Ciphertext.length() != RandomKey.length()) {
            return "Error: Key length must match ciphertext length";
        }

        StringBuilder Plaintext = new StringBuilder();
        int keyIndex = 0;

        for (char c : Ciphertext.toCharArray()) {
            if (Character.isLetter(c)) {
                int shift = RandomKey.charAt(keyIndex % RandomKey.length()) - 'A'; // A = 65
                char decryptedChar;
                if (Character.isUpperCase(c)) {
                    decryptedChar = (char) ((c - 'A' - shift + 26) % 26 + 'A');
                } else {
                    decryptedChar = (char) ((c - 'a' - shift + 26) % 26 + 'a');
                }
                Plaintext.append(decryptedChar);
                keyIndex++;
            } else {
                Plaintext.append(c);
            }
        }

        return Plaintext.toString();
    }

    static String generateRandomKey(int length) {
        StringBuilder key = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int randomInt = (int) (Math.random() * 26); // 0-25 for A-Z
            char c = (char) ('A' + randomInt); // Uppercase (A-Z)
            key.append(c);
        }
        return key.toString(); // Return uppercase-only key
    }

    private static String HandelPlainText(String input) {
        return input.replace(" ", ""); // Removes all spaces
    }

    public static String validateEncryptionResult(String[] result) {
        if (result == null || result.length != 2) {
            return "Error: Invalid encryption result.";
        }

        String ciphertext = result[0];
        String key = result[1];

        if (ciphertext.isEmpty()) {
            return "Error: Ciphertext cannot be empty.";
        }
        if (key.isEmpty()) {
            return "Error: Key cannot be empty.";
        }
        if (!isKeyValid(key)) {
            return "Error: Key must consist of uppercase letters only (A-Z).";
        }
        if (ciphertext.length() != key.length()) {
            return "Error: Key length must match ciphertext length.";
        }

        return ""; // No errors
    }

    private static boolean isKeyValid(String key) {
        return key.matches("^[a-zA-Z]+$"); // Key must consist of letters only
    }

    public static void main(String[] args) {
        String plaintext = "Hello World! 123$%^&*()";
        String[] result = One_Time_Pad_Encryption(plaintext);

        // Extract ciphertext and random key
        String ciphertext = result[0];
        String randomKey = result[1];

        System.out.println("Plaintext: " + plaintext);
        System.out.println("Random Key: " + randomKey);
        System.out.println("Ciphertext: " + ciphertext);

        // Decrypt ciphertext using the same random key
        String decryptedText = One_Time_Pad_Decryption(ciphertext, randomKey);
        System.out.println("Decrypted Text: " + decryptedText);
    }
}