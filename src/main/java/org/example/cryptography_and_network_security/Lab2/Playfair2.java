package org.example.cryptography_and_network_security.Lab2;

import java.util.Arrays;
import java.util.Scanner;

public class Playfair2 {
    // Removing the duplicate values from the key
        static String removeDuplicate(String s)
        {

            int j, index = 0, len = s.length();

            char c[] = s.toCharArray();

            for (int i = 0; i < len; i++) {

                for (j = 0; j < i; j++) {

                    if (c[i] == c[j])

                        break;
                }

                if (i == j)

                    c[index++] = c[i];
            }

            s = new String((Arrays.copyOf(c, index)));

            return s;
        }

        // Method 1
        // Removing the white spaces from string 'st'
        // which was replaced by the key as space.
        static String removeWhiteSpace(char[] ch, String key)
        {

            char[] c = key.toCharArray();

            // removing character which are input by the user
            // from string st

            for (int i = 0; i < c.length; i++) {

                for (int j = 0; j < ch.length; j++) {

                    if (c[i] == ch[j])

                        c[i] = ' ';
                }
            }

            key = new String(c);

            key = key.replaceAll(" ", "");

            return key;
        }

        // Method 2
        // To make the pair for encryption in plaintext.
        static String makePair(String pt)
        {

            String s = "";

            char c = 'a';

            for (int i = 0; i < pt.length(); i++) {

                if (pt.charAt(i) == ' ')

                    continue;

                else {

                    c = pt.charAt(i);

                    s += pt.charAt(i);
                }

                if (i < pt.length() - 1)

                    if (pt.charAt(i) == pt.charAt(i + 1))

                        s += "x";
            }

            // If plain text length is odd then
            // adding x to make length even.
            if (s.length() % 2 != 0)

                s += "x";

            System.out.println(s);

            return s;
        }

        // Method 3
        // To find the position of row and column in matrix
        // for encryption of the pair.
        static int[] findIJ(char a, char b, char x[][])
        {

            int[] y = new int[4];

            if (a == 'j')

                a = 'i';

            else if (b == 'j')

                b = 'i';

            for (int i = 0; i < 5; i++) {

                for (int j = 0; j < 5; j++) {

                    if (x[i][j] == a) {

                        y[0] = i;

                        y[1] = j;
                    }

                    else if (x[i][j] == b) {

                        y[2] = i;

                        y[3] = j;
                    }
                }
            }

            if (y[0] == y[2]) {

                y[1] += 1;

                y[3] += 1;
            }

            else if (y[1] == y[3]) {

                y[0] += 1;

                y[2] += 1;
            }

            for (int i = 0; i < 4; i++)

                y[i] %= 5;

            return y;
        }

        // Method 4
        // To encrypt the plaintext
        static String encrypt(String pt, char x[][])
        {

            char ch[] = pt.toCharArray();

            int a[] = new int[4];

            for (int i = 0; i < pt.length(); i += 2) {

                if (i < pt.length() - 1) {

                    a = findIJ(pt.charAt(i), pt.charAt(i + 1),
                            x);

                    if (a[0] == a[2]) {

                        ch[i] = x[a[0]][a[1]];

                        ch[i + 1] = x[a[0]][a[3]];
                    }

                    else if (a[1] == a[3]) {

                        ch[i] = x[a[0]][a[1]];

                        ch[i + 1] = x[a[2]][a[1]];
                    }

                    else {

                        ch[i] = x[a[0]][a[3]];

                        ch[i + 1] = x[a[2]][a[1]];
                    }
                }
            }

            pt = new String(ch);

            return pt;
        }

        // Method 5
        // Main driver method
        public static void main(String[] args)
        {

            // Creating an Scanner class object to
            // take input from user
            Scanner sc = new Scanner(System.in);

            String pt = "instruments";

            // Key input
            String key = "monarchy";

            key = removeDuplicate(key);

            char[] ch = key.toCharArray();

            // Reading string array of Letters of english
            // alphabet as Playfair to implement
            String st = "abcdefghiklmnopqrstuvwxyz";

            st = removeWhiteSpace(ch, st);

            char[] c = st.toCharArray();

            // Matrix input using above key
            char[][] x = new char[5][5];

            int indexOfSt = 0, indexOfKey = 0;

            for (int i = 0; i < 5; i++) {

                for (int j = 0; j < 5; j++) {

                    if (indexOfKey < key.length())

                        x[i][j] = ch[indexOfKey++];

                    else

                        x[i][j] = c[indexOfSt++];
                }
            }

            // Printing Matrix

            for (int i = 0; i < 5; i++) {

                for (int j = 0; j < 5; j++)

                    System.out.print(x[i][j] + " ");

                System.out.println();
            }

            // For getting encrypted output

            // Calling makePair() method over object created in
            // main()
            pt = makePair(pt);

            // Calling makePair() method over object created in
            // main()
            pt = encrypt(pt, x);

            // Print and display in the console
            System.out.println(pt);
        }
    }


