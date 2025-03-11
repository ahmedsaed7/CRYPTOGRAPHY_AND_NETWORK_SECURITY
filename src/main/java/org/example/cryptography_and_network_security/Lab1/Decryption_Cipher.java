package org.example.cryptography_and_network_security.Lab1;
public class Decryption_Cipher {
    public static String DECRYPTION(String  ciphertext , int Key ){
      StringBuilder decryptionText = new StringBuilder();
      for(char c : ciphertext.toCharArray()){
          if(Character.isUpperCase(c)){
            int OriginalIndex = c - 'A';
            int decryptedIndex =  (OriginalIndex - Key + 26) % 26;
            char decryptedChar = (char) (decryptedIndex +'A');
            decryptionText.append(decryptedChar);
          }
          else if(Character.isLowerCase(c)){
              int OriginalIndex = c - 'a';
              int decryptedIndex =  (OriginalIndex - Key + 26) % 26;
              char decryptedChar = (char) (decryptedIndex +'a');
              decryptionText.append(decryptedChar);
          }
          else {
              decryptionText.append(c);
          }
      }
      return decryptionText.toString();
    }

    public static void main(String[] args) {
        // Define the ciphertext and key.
        String ciphertext = "Khoor";
        int key = 3;
        String decryptedText = Decryption_Cipher.DECRYPTION(ciphertext, key);
        System.out.println("Decrypted Text: " + decryptedText);
    }
}
