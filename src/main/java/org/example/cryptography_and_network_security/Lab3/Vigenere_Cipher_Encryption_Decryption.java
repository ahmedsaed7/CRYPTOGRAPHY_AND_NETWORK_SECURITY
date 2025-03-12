package org.example.cryptography_and_network_security.Lab3;

/**
 * 'A' = 65
 * 'B' = 66
 * 'C' = 67
 * 'D' = 68
 * 'E' = 69
 * 'F' = 70
 * 'G' = 71
 * 'H' = 72
 * 'I' = 73
 * 'J' = 74
 * 'K' = 75
 * 'L' = 76
 * 'M' = 77
 * 'N' = 78
 * 'O' = 79
 * 'P' = 80
 * 'Q' = 81
 * 'R' = 82
 * 'S' = 83
 * 'T' = 84
 * 'U' = 85
 * 'V' = 86
 * 'W' = 87
 * 'X' = 88
 * 'Y' = 89
 * 'Z' = 90
 *
 * 'a' = 97
 * 'b' = 98
 * 'c' = 99
 * 'd' = 100
 * 'e' = 101
 * 'f' = 102
 * 'g' = 103
 * 'h' = 104
 * 'i' = 105
 * 'j' = 106
 * 'k' = 107
 * 'l' = 108
 * 'm' = 109
 * 'n' = 110
 * 'o' = 111
 * 'p' = 112
 * 'q' = 113
 * 'r' = 114
 * 's' = 115
 * 't' = 116
 * 'u' = 117
 * 'v' = 118
 * 'w' = 119
 * 'x' = 120
 * 'y' = 121
 * 'z' = 122
 * Space = 32
 * Exclamation mark ('!') = 33
 * Quotation mark ('"') = 34
 * Hash ('#') = 35
 * Dollar sign ('$') = 36
 * Percent sign ('%') = 37
 * Ampersand ('&') = 38
 * Apostrophe (''') = 39
 * Parentheses ('(' and ')') = 40 and 41
 * Comma (',') = 44
 * Period ('.') = 46
 * Slash ('/') = 47
 * Colon (':') = 58
 * Semicolon (';') = 59
 * Less than ('<') = 60
 * Equal sign ('=') = 61
 * Greater than ('>') = 62
 * Question mark ('?') = 63
 * At symbol ('@') = 64
 * */
public class Vigenere_Cipher_Encryption_Decryption {
    public static String Vigenere_Cipher_Encryption(String plainText, String Key){
        StringBuilder ciphertext = new StringBuilder();
        Key = processKey(Key ,plainText);
        int keyIndex = 0;
        for (char c : plainText.toCharArray()) {
            if (Character.isLetter(c)) {
                int shift = Key.charAt(keyIndex % Key.length() ) - 'A' ; // A = 65
                char encryptedChar;
                if(Character.isUpperCase(c)) {
                    encryptedChar = (char) ((c - 'A' + shift) % 26 + 'A');
                }
                else {
                    encryptedChar = (char) ((c - 'a' + shift) % 26 + 'a'); //a=97
                }
                ciphertext.append(encryptedChar);
                keyIndex++;
            } else {
                ciphertext.append(c);
            }
        }
        return ciphertext.toString();
    }
    public static String Vigenere_Cipher_Decryption(String cipherText, String key) {
        StringBuilder plaintext = new StringBuilder();
        key = processKey(key ,cipherText);
        int keyIndex = 0;
        for (char c : cipherText.toCharArray()) {
            if (Character.isLetter(c)) {
                int shift =key.charAt(keyIndex % key.length() ) - 'A' ; // A = 65
                char decryptedChar;
                if(Character.isUpperCase(c)) {
                    decryptedChar = (char) ((c - 'A' - shift +26) % 26 + 'A');
                }
                else {
                    decryptedChar = (char) ((c - 'a' - shift +26 ) % 26 + 'a'); //a=97
                }
                plaintext.append(decryptedChar);
                keyIndex++;
            } else {
                plaintext.append(c);
            }
        }
        return plaintext.toString();
    }
    // Remove spaces and non-letters, convert to uppercase
    public static String processKey(String key ,String text) {
        if(key == null || key.isEmpty()) {
            System.out.println("Error: Key cannot be empty.");
            return text;
        }
        return key.replaceAll("[^a-zA-Z]", "").toUpperCase();
    }
    public static void main(String[] args) {
        String plainText = "wearediscoveredsaveyourself";
        String key = "deceptive";
//        System.out.println(processKey(key , plainText));
        String encryptedText = Vigenere_Cipher_Encryption(plainText, key);
        System.out.println("Encrypted Text: " + encryptedText);
        String decryptedText = Vigenere_Cipher_Decryption(encryptedText, key);
        System.out.println("Decrypted Text: " + decryptedText);
    }

}
