package com.company;

import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {

        String oriFilename = "G:\\alice29.txt";
        String bwtFilename = "G:\\alice29.txt.bwt";
        String rleFilename = "G:\\alice29.txt.bwt.rle";
        String hufFilename = "G:\\alice29.txt.bwt.rle.huf";

        String oriFilename2 = "G:\\alice29_2.txt";
        String bwtFilename2 = "G:\\alice29_2.txt.bwt";
        String rleFilename2 = "G:\\alice29_2.txt.bwt.rle";

        InputStream in = new FileInputStream(oriFilename);
        OutputStream out = new FileOutputStream(bwtFilename);

        BurrowsWheeler.encode(in, out);

        in = new FileInputStream(bwtFilename);
        out = new FileOutputStream(rleFilename);

        RunLength.compress(in, out);

        in = new FileInputStream(rleFilename);
        out = new FileOutputStream(hufFilename);

        Huffman.compress(in, out);


        in = new FileInputStream(hufFilename);
        out = new FileOutputStream(rleFilename2);

        Huffman.expand(in, out);

        in = new FileInputStream(rleFilename2);
        out = new FileOutputStream(bwtFilename2);

        RunLength.expand(in, out);

        in = new FileInputStream(bwtFilename2);
        out = new FileOutputStream(oriFilename2);

        BurrowsWheeler.decode(in, out);

        System.out.println("fim");
    }
}
