package org.example.cryptography_and_network_security.Lab1;

public class Encryption_Cipher {
    public static String ENCRYPTION(String PlainText, int Key) {
        StringBuilder encryptionText = new StringBuilder();
        for(char c : PlainText.toCharArray()){
            if (Character.isUpperCase(c)) {
                int OriginalIndex = c - 'A';  //65 in Ascii value
                System.out.println("The ASCII value of A: "+(int)'A');
                System.out.println("The index of Character: " +(int)c);
                System.out.println("Original Index: " + OriginalIndex);
                int encryptedIndex = (OriginalIndex + Key) % 26;
                System.out.println("Encrypted Index: " + encryptedIndex);
                char encryptedChar = (char) (encryptedIndex + 'A');
                encryptionText.append(encryptedChar);
            } else if (Character.isLowerCase(c)) {
                int OriginalIndex = c - 'a';
                int encryptedIndex = (OriginalIndex + Key) % 26;
                char encryptedChar = (char) (encryptedIndex + 'a');
                encryptionText.append(encryptedChar);
            }
            else {
                encryptionText.append(c);
            }
        }

    return encryptionText.toString();
    }
    public static void main(String[] args) {
        String plaintext = "HEllo";
        int key = 3;
        String encryptedText = Encryption_Cipher.ENCRYPTION(plaintext, key);
        System.out.println("Encrypted Text: " + encryptedText);
    }
}
