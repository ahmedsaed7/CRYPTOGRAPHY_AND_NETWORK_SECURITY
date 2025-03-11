package org.example.cryptography_and_network_security.Lab1;

public class BruteForceDecryption {
    public static String BRUTEFORCE(String CipherText) {
        StringBuilder result = new StringBuilder();
        result.append("The bruteforce is:- \n");
        for (int key = 0 ; key < 26 ; key++){
            String decryptedText = Decryption_Cipher.DECRYPTION(CipherText, key);
            result.append("Key ").append(key).append(": ").append(decryptedText).append("\n");
        }
        return result.toString();
        }
    public static void main(String[] args) {
        // Example usage
        String ciphertext = "KHOOR";
        String ciphertext1 = "khoor";
        String BruteForceDecryption = org.example.cryptography_and_network_security.Lab1.BruteForceDecryption.BRUTEFORCE(ciphertext);
        String BruteForceDecryption1 = org.example.cryptography_and_network_security.Lab1.BruteForceDecryption.BRUTEFORCE(ciphertext1);

        System.out.println(BruteForceDecryption);
        System.out.println(BruteForceDecryption1);

    }
    }

