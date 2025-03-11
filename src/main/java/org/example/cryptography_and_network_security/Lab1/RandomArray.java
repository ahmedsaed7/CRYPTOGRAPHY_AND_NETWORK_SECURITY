package org.example.cryptography_and_network_security.Lab1;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomArray {
    public static void WriteToFile(String filename, String text) {
        try (FileWriter writer = new FileWriter(filename, true)) {
            writer.write(text + "\n");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public static String RandomArray() {
        List<Character> alphabet = new ArrayList<>();
        for (char c = 'A'; c <= 'Z'; c++) {
            alphabet.add(c);
        }
        Collections.shuffle(alphabet);

        StringBuilder randomKey = new StringBuilder();
        for (char c : alphabet) {
            randomKey.append(c);
            // Write each character to the file on a new line
            WriteToFile("randomkey.txt", String.valueOf(c));
        }

        return randomKey.toString();
    }

    public static void main(String[] args) {
        String key = RandomArray();
        System.out.println("Generated Key: " + key);
    }
}
