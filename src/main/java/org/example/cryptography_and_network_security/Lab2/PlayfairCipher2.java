package org.example.cryptography_and_network_security.Lab2;

public class PlayfairCipher2 {

    public static void main(String[] args) {
        String plaintext = "HELLO";
        String key = "PLAYFAIR";

        // Step 1: Generate the 5x5 matrix
        char[][] matrix = createMatrix(key.toUpperCase().replace("J", "I"));
        printMatrix(matrix);

        // Step 2: Prepare the plaintext
        String preparedText = preparePlainText(plaintext);
        System.out.println("Prepared Plaintext: " + preparedText);

        // Step 3: Encrypt the plaintext
        String encryptedText = encrypt(matrix, preparedText);
        System.out.println("Encrypted Text: " + encryptedText);
    }

    // Step 1: Create the 5x5 matrix using the key
    private static char[][] createMatrix(String key) {
        char[][] matrix = new char[5][5];
        boolean[] used = new boolean[26]; // To track used letters (A-Z)

        int index = 0;

        // Add characters from the key to the matrix
        for (char c : key.toCharArray()) {
            if (!used[c - 'A']) { // If the character hasn't been used
                matrix[index / 5][index % 5] = c;
                used[c - 'A'] = true;
                index++;
            }
        }

        // Add remaining alphabet characters (excluding 'J')
        for (char c = 'A'; c <= 'Z'; c++) {
            if (c != 'J' && !used[c - 'A']) {
                matrix[index / 5][index % 5] = c;
                used[c - 'A'] = true;
                index++;
            }
        }

        return matrix;
    }

    // Print the 5x5 matrix
    private static void printMatrix(char[][] matrix) {
        System.out.println("Key Matrix:");
        for (char[] row : matrix) {
            for (char c : row) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }

    // Step 2: Prepare the plaintext for encryption
    private static String preparePlainText(String text) {
        StringBuilder result = new StringBuilder(text.toUpperCase().replace("J", "I").replaceAll("[^A-Z]", ""));

        // Insert 'X' between repeated characters
        for (int i = 0; i < result.length() - 1; i += 2) {
            if (result.charAt(i) == result.charAt(i + 1)) {
                result.insert(i + 1, 'X');
            }
        }

        // Add 'X' if the plaintext has an odd number of characters
        if (result.length() % 2 != 0) {
            result.append('X');
        }

        return result.toString();
    }

    // Step 3: Encrypt the plaintext using the Playfair cipher
    private static String encrypt(char[][] matrix, String text) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < text.length(); i += 2) {
            char a = text.charAt(i);
            char b = text.charAt(i + 1);

            int[] posA = findPosition(matrix, a);
            int[] posB = findPosition(matrix, b);

            // Same row: Move to the right (wrap around if needed)
            if (posA[0] == posB[0]) {
                result.append(matrix[posA[0]][(posA[1] + 1) % 5]);
                result.append(matrix[posB[0]][(posB[1] + 1) % 5]);
            }
            // Same column: Move down (wrap around if needed)
            else if (posA[1] == posB[1]) {
                result.append(matrix[(posA[0] + 1) % 5][posA[1]]);
                result.append(matrix[(posB[0] + 1) % 5][posB[1]]);
            }
            // Rectangle rule: Swap columns
            else {
                result.append(matrix[posA[0]][posB[1]]);
                result.append(matrix[posB[0]][posA[1]]);
            }
        }

        return result.toString();
    }

    // Find the position of a character in the matrix
    private static int[] findPosition(char[][] matrix, char c) {
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                if (matrix[row][col] == c) {
                    return new int[]{row, col};
                }
            }
        }
        return new int[]{-1, -1}; // Not found
    }
}