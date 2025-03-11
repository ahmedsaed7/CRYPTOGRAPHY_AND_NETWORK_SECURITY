package org.example.cryptography_and_network_security.Lab1;

public class MonoalphabeticCipherEncrypt {
    public static String  MONOALPHABETICCIPHERENCRYPTION (String Plaintext , String RamdomKey ){
        StringBuilder encryptionText = new StringBuilder();
        String upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerAlphabet = "abcdefghijklmnopqrstuvwxyz";
        String upperKey = RamdomKey.toUpperCase();
        String lowerKey = RamdomKey.toLowerCase();
        for (char c : Plaintext.toCharArray()) {
            if (Character.isUpperCase(c)) {
                int index = upperAlphabet.indexOf(c);
                encryptionText.append(upperKey.charAt(index));
            } else if (Character.isLowerCase(c)) {
                int index = lowerAlphabet.indexOf(c);
                encryptionText.append(lowerKey.charAt(index));
            }else {
                encryptionText.append(c);
            }
        }
        return encryptionText.toString() ;
    }
    public static void main(String[] args) {
        String Plaintext = "Hello World!";
        String KandomKey = "dkvqfibjwpescxhtmyauoirgzn";
        String EncryptText = MONOALPHABETICCIPHERENCRYPTION(Plaintext , KandomKey);
        System.out.println("cipher text: " + EncryptText );
    }
}
