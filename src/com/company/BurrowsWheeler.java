package com.company;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by klein-desk on 23-Sep-17.
 */
public class BurrowsWheeler {

    private static final int R = 256; // Radix of a byte.

    /**
     * apply Burrows-Wheeler encoding, reading from standard input and writing to standard output
     */
    public static void encode(InputStream in, OutputStream out) {

        BinaryStdIn stdIn = new BinaryStdIn(in);
        BinaryStdOut stdOut = new BinaryStdOut(out);

        String s = stdIn.readString();
        CircularSuffixArray csa = new CircularSuffixArray(s);
        int first = 0;
        while (first < csa.length() && csa.index(first) != 0) {
            first++;
        }
        stdOut.write(first);
        for (int i = 0; i < csa.length(); i++) {
            stdOut.write(s.charAt((csa.index(i) + s.length() - 1) % s.length()));
        }
        stdOut.close();
    }

    /**
     * apply Burrows-Wheeler decoding, reading from standard input and writing to standard output
     */
    public static void decode(InputStream in, OutputStream out) {

        BinaryStdIn stdIn = new BinaryStdIn(in);
        BinaryStdOut stdOut = new BinaryStdOut(out);

        int first = stdIn.readInt();
        String t = stdIn.readString();
        int n = t.length();
        int[] count = new int[R + 1], next = new int[n];
        for (int i = 0; i < n; i++)
            count[t.charAt(i) + 1]++;
        for (int i = 1; i < R + 1; i++)
            count[i] += count[i - 1];
        for (int i = 0; i < n; i++)
            next[count[t.charAt(i)]++] = i;
        for (int i = next[first], c = 0; c < n; i = next[i], c++)
            stdOut.write(t.charAt(i));
        stdOut.close();
    }

    /**
     * if args[0] is '-', apply Burrows-Wheeler encoding. if args[0] is '+', apply Burrows-Wheeler decoding
     *
     * @param args
     */
    /*
    public static void main(String[] args) {
        if (args.length != 1)
            throw new IllegalArgumentException("Expected + or -\n");
        else if (args[0].equals("+"))
            decode();
        else if (args[0].equals("-"))
            encode();
        else {
            String msg = "Unknown argument: " + args[0] + "\n";
            throw new IllegalArgumentException(msg);
        }
    }
    */

}
