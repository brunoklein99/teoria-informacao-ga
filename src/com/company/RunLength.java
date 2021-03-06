package com.company;

/******************************************************************************
 *  Compilation:  javac RunLength.java
 *  Execution:    java RunLength - < input.txt   (compress)
 *  Execution:    java RunLength + < input.txt   (expand)
 *  Dependencies: BinaryIn.java BinaryOut.java
 *  Data files:   http://algs4.cs.princeton.edu/55compression/4runs.bin
 *                http://algs4.cs.princeton.edu/55compression/q32x48.bin
 *                http://algs4.cs.princeton.edu/55compression/q64x96.bin
 *
 *  Compress or expand binary input from standard input using
 *  run-length encoding.
 *
 *  % java BinaryDump 40 < 4runs.bin
 *  0000000000000001111111000000011111111111
 *  40 bits
 *
 *  This has runs of 15 0s, 7 1s, 7 0s, and 11 1s.
 *
 *  % java RunLength - < 4runs.bin | java HexDump
 *  0f 07 07 0b
 *  4 bytes
 *
 ******************************************************************************/

import java.io.*;

/**
 *  The {@code RunLength} class provides static methods for compressing
 *  and expanding a binary input using run-length coding with 8-bit
 *  run lengths.
 *  <p>
 *  For additional documentation,
 *  see <a href="http://algs4.cs.princeton.edu/55compress">Section 5.5</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public class RunLength {
    private static final int R = 256;
    private static final int LG_R = 8;

    // Do not instantiate.
    private RunLength() {
    }

    /**
     * Reads a sequence of bits from standard input (that are encoded
     * using run-length encoding with 8-bit run lengths); decodes them;
     * and writes the results to standard output.
     */
    public static void expand(InputStream in, OutputStream out) throws IOException {

        while (in.available() > 0) {
            int count = in.read();
            int value = in.read();
            for (int i = 0; i < count; i++) {
                out.write(value);
            }
        }

    }

    /**
     * Reads a sequence of bits from standard input; compresses
     * them using run-length coding with 8-bit run lengths; and writes the
     * results to standard output.
     */
    public static void compress(InputStream in, OutputStream out) throws IOException {

        int size = in.available();
        byte[] buffer = new byte[size];

        in.read(buffer, 0, buffer.length);

        for (int i = 0; i < buffer.length; i++) {
            int runLength = 1;
            while (i + 1 < buffer.length && buffer[i] == buffer[i + 1]) {
                runLength++;
                i++;
            }
            if (runLength > 255) {
                for (int j = 0; j < runLength / 255; j++) {
                    out.write(255);
                    out.write(buffer[i]);
                }
                out.write(runLength % 255);
                out.write(buffer[i]);
            } else {
                out.write(runLength);
                out.write(buffer[i]);
            }
        }
    }


    /**
     * Sample client that calls {@code compress()} if the command-line
     * argument is "-" an {@code expand()} if it is "+".
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) throws IOException {
        InputStream in = new FileInputStream("G:\\alice29.txt.bwt");
        OutputStream out = new FileOutputStream("G:\\alice29.txt.bwt.rle");

        compress(in, out);

        in = new FileInputStream("G:\\alice29.txt.bwt.rle");
        out = new FileOutputStream("G:\\alice29.txt.bwt2");

        expand(in, out);
    }
}