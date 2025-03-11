package org.example.cryptography_and_network_security.Lab1;

public class MonoalphabeticCipherDecrypt {
    public static String  MONOALPHABETICCIPHERDECRYPTION  (String ciphertext , String RamdomKey ){
        StringBuilder decryptedText = new StringBuilder();
        String upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerAlphabet = "abcdefghijklmnopqrstuvwxyz";
        String upperKey = RamdomKey.toUpperCase();
        String lowerKey = RamdomKey.toLowerCase();
        for (char c : ciphertext.toCharArray()) {
            if (Character.isUpperCase(c)) {
                int index = upperKey.indexOf(c);
                decryptedText.append(upperAlphabet.charAt(index));
            } else if (Character.isLowerCase(c)) {
                int index = lowerKey.indexOf(c);
                decryptedText.append(lowerAlphabet.charAt(index));
            }else {
                decryptedText.append(c);
            }

        }
        return decryptedText.toString();
    }
    public static void main(String[] args) {
        String ciphertext = "Jfssh Rhysq";
        String KandomKey = "dkvqfibjwpescxhtmyauoirgzn";
        String DecriptionText = MONOALPHABETICCIPHERDECRYPTION(ciphertext , KandomKey);
        System.out.println("cipher text: " + DecriptionText );
    }
}
