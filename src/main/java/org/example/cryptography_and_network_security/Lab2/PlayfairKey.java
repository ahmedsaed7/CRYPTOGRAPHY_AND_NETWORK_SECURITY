package org.example.cryptography_and_network_security.Lab2;

public  class PlayfairKey  {
    public static void main(String[] args) {
//        String plaintext = "HELLO";
//        String key = "PLAYFAIR";
//        String encrypted = PlayfairEncryption(plaintext, key);
//        System.out.println("Encrypted: " + encrypted);

        PlayfairKey playfairKey = new PlayfairKey("hello");
        playfairKey.print();
//        System.out.println(playfairKey.clean("hello")); // Example call to the clean method
//        System.out.println(playfairKey.Encript("hello").toUpperCase());

        String plaintext = "ballon";
        String encrypted = playfairKey.Encript(plaintext);
        System.out.println("Encrypted: " + encrypted);

        String decrypted = playfairKey.Dencript(encrypted);
        System.out.println("Decrypted: " + decrypted);
    }

    public static final int Matrix_Size = 5 ;
    public final char[][] KeyMatrix = new char[Matrix_Size][Matrix_Size];

    public PlayfairKey(String key) {
        buildKeymatrix(key);
    }

    private void buildKeymatrix(String key) {
        key = cleanKey(key);
        int index = 0;
        char ch = 'A';
        boolean[] flag = new boolean[26]; // check if the   characters is used or not
        flag['J' - 'A'] = true;//replace j by i
        for (int i = 0; i < Matrix_Size; i++) {
            for (int j = 0; j < Matrix_Size; j++) {
                if (index < key.length()) {
                    KeyMatrix[i][j] = key.charAt(index); // add ch from clean kay
                    flag[key.charAt(index) - 'A'] = true;
                    index++;
                } else {
                    while (flag[ch - 'A']) { // if ch is find next ch
                        ch++;
                    }
                    KeyMatrix[i][j] = ch;
                    flag[ch - 'A'] = true;
                    ch++;
                }
            }
        }
    }

    private String cleanKey(String key) {
        key = key.toUpperCase().replace('J', 'I');
        boolean[] flag = new boolean[26];
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < key.length(); i++) {
            char ch = key.charAt(i);
            if (ch >= 'A' && ch <= 'Z') {
                int index = ch - 'A';
                if (!flag[index]) { // add the character to the result if itis not found
                    flag[index] = true;
                    result.append(ch);
                }
            }
        }
        return result.toString();
    }

    public void print() {
        for (int i = 0; i < Matrix_Size; i++) {
            for (int j = 0; j < Matrix_Size; j++) {
                System.out.print(KeyMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    private  int rowPosition(char ch){
        for (int i = 0; i < Matrix_Size; i++) {
            for (int j = 0; j < Matrix_Size; j++) {
                if (ch == KeyMatrix[i][j]){
                    return i;
                }
            }
        }
        return -1;
    }
    private  int columePosition(char ch){
        for (int i = 0; i < Matrix_Size; i++) {
            for (int j = 0; j < Matrix_Size; j++) {
                if (ch == KeyMatrix[i][j]){
                    return j;
                }
            }
        }
        return -1;
    }

    public String encrypt(char ch1, char ch2) {
        int i1 = rowPosition(ch1);
        int j1 = columePosition(ch1);
        int i2 = rowPosition(ch2);
        int j2 = columePosition(ch2);
        if (i1 == -1 || j1 == -1 || i2 == -1 || j2 == -1) {
            throw new IllegalArgumentException("Invalid characters in plaintext.");
        }
        if (i1 == i2) { // Same row
            return "" + KeyMatrix[i1][(j1 + 1) % 5] + KeyMatrix[i2][(j2 + 1) % 5];
        } else if (j1 == j2) { // Same column
            return "" + KeyMatrix[(i1 + 1) % 5][j1] + KeyMatrix[(i2 + 1) % 5][j2];
        } else { // Rectangle
            return "" + KeyMatrix[i1][j2] + KeyMatrix[i2][j1];
        }
    }
    public String Encript(String plaintext){
        plaintext = clean(plaintext);
        StringBuilder ciphertext = new StringBuilder();
        for (int i = 0; i < plaintext.length(); i += 2) {
            char ch1 = plaintext.charAt(i);
            char ch2 = plaintext.charAt(i + 1);
            ciphertext.append(encrypt(ch1, ch2));
        }
        return ciphertext.toString();
    }
//    private String clean(String plaintext){
//        plaintext = plaintext.toLowerCase().replace('J', 'I');
//        StringBuilder result = new StringBuilder();
//        boolean flag =  plaintext.length()%2==1;
//
//        for (int i = 0; i < plaintext.length(); i+=2) {
//            if (plaintext.charAt(i) != plaintext.charAt(i+1)) {
//                result.append( plaintext.charAt(i) +"" +plaintext.charAt(i+1));
//            }else {
//                flag = !flag;
//                result.append( plaintext.charAt(i) + "x");
//                i--;
//            }
//        }
//        if (flag) {
//            result.append( plaintext.charAt(plaintext.length()-1 ) + "x");
//        }
//
//        return result.toString();
//    }
    private String clean(String text) {
        text = text.toUpperCase().replace('J', 'I');
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (ch >= 'A' && ch <= 'Z') {
                if (result.length() > 0 && result.charAt(result.length() - 1) == ch) {
                    result.append('X');
                }
                result.append(ch);
            }
        }
        if (result.length() % 2 != 0) {
            result.append('X');
        }

        return result.toString();
    }

    public String dencrypt(char ch1, char ch2) {
        int i1 = rowPosition(ch1);
        int j1 = columePosition(ch1);
        int i2 = rowPosition(ch2);
        int j2 = columePosition(ch2);
        if (i1 == -1 || j1 == -1 || i2 == -1 || j2 == -1) {
            throw new IllegalArgumentException("Invalid characters in ciphertext.");
        }
        if (i1 == i2) { // Same row
            return "" + KeyMatrix[i1][(j1 + 4) % 5] + KeyMatrix[i2][(j2 + 4) % 5];
        } else if (j1 == j2) { // Same column
            return "" + KeyMatrix[(i1 + 4) % 5][j1] + KeyMatrix[(i2 + 4) % 5][j2];
        } else { // Rectangle
            return "" + KeyMatrix[i1][j2] + KeyMatrix[i2][j1];
        }
    }

    public String Dencript(String ciphertext) {
        ciphertext = clean(ciphertext);
        StringBuilder plaintext = new StringBuilder();
        for (int i = 0; i < ciphertext.length(); i += 2) {
            char ch1 = ciphertext.charAt(i);
            char ch2 = ciphertext.charAt(i + 1);
            plaintext.append(dencrypt(ch1, ch2));
        }
        return cleanupDecryptedText(plaintext.toString());
    }

    private String cleanupDecryptedText(String decryptedText) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < decryptedText.length(); i++) {
            char ch = decryptedText.charAt(i);

            // Skip 'X' if it was inserted between duplicate characters
            if (i > 0 && i + 1 < decryptedText.length() && decryptedText.charAt(i - 1) == decryptedText.charAt(i + 1) && ch == 'X') {
                continue;
            }

            result.append(ch);
        }

        // Remove 'X' if  length even
        if (result.length() > 0 && result.charAt(result.length() - 1) == 'X') {
            result.deleteCharAt(result.length() - 1);
        }

        return result.toString();
    }

//    public static String PlayfairEncryption(String plaintext, String textkey) {
//        char[][] Matrix = generateKeyMatrix(textkey.toUpperCase().replace("J", "I"));
//        System.out.println("Generated Key Matrix:");
//        printMatrix(Matrix);
//
//        plaintext = preparePlainText(plaintext);
//        System.out.println("Processed Plaintext: " + plaintext);
//
//        return encryptPairs(Matrix, plaintext);
//    }
//
//    private static char[][] generateKeyMatrix(String key) {
//        char[][] matrix = new char[5][5];
//        String keyString = (key + "ABCDEFGHIKLMNOPQRSTUVWXYZ").replaceAll("[^A-Z]", "");
//        HashSet<Character> used = new HashSet<>();
//        int index = 0;
//
//        for (char c : keyString.toCharArray()) {
//            if (!used.contains(c) && c != 'J') {
//                matrix[index / 5][index % 5] = c;
//                used.add(c);
//                index++;
//                if (index == 25) break;
//            }
//        }
//        return matrix;
//    }
//
//    private static String encryptPairs(char[][] matrix, String text) {
//        StringBuilder result = new StringBuilder();
//        for (int i = 0; i < text.length(); i += 2) {
//            char a = text.charAt(i);
//            char b = text.charAt(i + 1);
//            int[] posA = findPosition(matrix, a);
//            int[] posB = findPosition(matrix, b);
//
//            System.out.printf("Pair: %c%c → Positions: [%d,%d] & [%d,%d]%n",
//                    a, b, posA[0], posA[1], posB[0], posB[1]);
//
//            // Same row
//            if (posA[0] == posB[0]) {
//                result.append(matrix[posA[0]][(posA[1] + 1) % 5]);
//                result.append(matrix[posB[0]][(posB[1] + 1) % 5]);
//            }
//            // Same column
//            else if (posA[1] == posB[1]) {
//                result.append(matrix[(posA[0] + 1) % 5][posA[1]]);
//                result.append(matrix[(posB[0] + 1) % 5][posB[1]]);
//            }
//            // Rectangle rule
//            else {
//                result.append(matrix[posA[0]][posB[1]]);
//                result.append(matrix[posB[0]][posA[1]]);
//            }
//        }
//        return result.toString();
//    }
//
//    private static int[] findPosition(char[][] matrix, char c) {
//        for (int row = 0; row < 5; row++) {
//            for (int col = 0; col < 5; col++) {
//                if (matrix[row][col] == c) {
//                    return new int[]{row, col};
//                }
//            }
//        }
//        return new int[]{-1, -1};
//    }
//
//    private static String preparePlainText(String text) {
//        // Clean the text
//        text = text.toUpperCase()
//                .replace("J", "I")
//                .replaceAll("[^A-Z]", "");  // Fixed regex
//        StringBuilder processedText = new StringBuilder(text);
//
//        // Insert X between duplicates and pad
//        for (int i = 0; i < processedText.length(); i += 2) {
//            if (i + 1 >= processedText.length()) {
//                processedText.append('X');
//                break;
//            }
//            if (processedText.charAt(i) == processedText.charAt(i + 1)) {
//                processedText.insert(i + 1, 'X');
//            }
//        }
//        return processedText.toString();
//    }
//
//    private static void printMatrix(char[][] matrix) {
//        for (int i = 0; i < 5; i++) {
//            for (int j = 0; j < 5; j++) {
//                System.out.print(matrix[i][j] + " ");
//            }
//            System.out.println();
//        }
//    }
//

}