package com.company;

import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        String inLzwFilenameCompress = "G:\\alice29.txt";
        String outLzwFilenameCompress = "G:\\alice29.lzw";
        String inHuffmanFilenameCompress = outLzwFilenameCompress;
        String outHuffmanFilenameCompress = "G:\\alice29.lzw.huffman";

        String inHuffmanFilenameDecompress = outHuffmanFilenameCompress;
        String outHuffmanFilenameDecompress = "G:\\alice29.decompress.lzw";
        String inLzwFilenameDecompress = outHuffmanFilenameDecompress;
        String outLzwFilenameDecompress = "G:\\alice29.decompress.txt";

        lzwCompress(inLzwFilenameCompress, outLzwFilenameCompress);
        huffmanCompress(inHuffmanFilenameCompress, outHuffmanFilenameCompress);

        huffmanDecompress(inHuffmanFilenameDecompress, outHuffmanFilenameDecompress);
        lzwDecompress(inLzwFilenameDecompress, outLzwFilenameDecompress);

        System.out.println("fim");
    }

    private static void lzwCompress(String inFilename, String outFilename) throws FileNotFoundException {
        InputStream in = new BufferedInputStream(new FileInputStream(inFilename));
        OutputStream out = new BufferedOutputStream(new FileOutputStream(outFilename));
        LZW.compress(in, out);
    }

    private static void lzwDecompress(String inFilename, String outFilename) throws FileNotFoundException {
        InputStream in = new BufferedInputStream(new FileInputStream(inFilename));
        OutputStream out = new BufferedOutputStream(new FileOutputStream(outFilename));
        LZW.expand(in, out);
    }

    private static void huffmanCompress(String inFilename, String outFilename) throws FileNotFoundException {
        InputStream in = new BufferedInputStream(new FileInputStream(inFilename));
        OutputStream out = new BufferedOutputStream(new FileOutputStream(outFilename));
        Huffman.compress(in, out);
    }

    private static void huffmanDecompress(String inFilename, String outFilename) throws FileNotFoundException {
        InputStream in = new BufferedInputStream(new FileInputStream(inFilename));
        OutputStream out = new BufferedOutputStream(new FileOutputStream(outFilename));
        Huffman.expand(in, out);
    }

    private static void runLengthCompress(String inFilename, String outFilename) throws FileNotFoundException {
        InputStream in = new BufferedInputStream(new FileInputStream(inFilename));
        OutputStream out = new BufferedOutputStream(new FileOutputStream(outFilename));
        RunLength.compress(in, out);
    }

    private static void runLengthDecompress(String inFilename, String outFilename) throws FileNotFoundException {
        InputStream in = new BufferedInputStream(new FileInputStream(inFilename));
        OutputStream out = new BufferedOutputStream(new FileOutputStream(outFilename));
        RunLength.expand(in, out);
    }
}
