package com.company;

import java.io.*;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;

public class Main {

    public static void main(String[] args) throws Exception {
        String inputFileHuffman = "C:\\Users\\klein-desk\\IdeaProjects\\informacaoga\\src\\com\\company\\alice29.txt";
        String outputFileHuffman = "C:\\Users\\klein-desk\\IdeaProjects\\informacaoga\\src\\com\\company\\alice29.huffman";
        String outputDeflate = "C:\\Users\\klein-desk\\IdeaProjects\\informacaoga\\src\\com\\company\\alice29.huffman.deflate";

        InputStream inHuffman = new BufferedInputStream(new FileInputStream(inputFileHuffman));
        OutputStream outHuffman = new BufferedOutputStream(new FileOutputStream(outputFileHuffman));

        Huffman.compress(inHuffman, outHuffman);

        InputStream inDeflate = new BufferedInputStream(new FileInputStream(outputFileHuffman));
        OutputStream outDeflate = new BufferedOutputStream(new FileOutputStream(outputDeflate));
        outDeflate = new DeflaterOutputStream(outDeflate);
        copy(inDeflate, outDeflate);

        System.out.println("fim");
    }

    public static void copy(InputStream is, OutputStream os) throws Exception {
        byte[] bytes = new byte[1024];
        int length;
        try {
            while ((length = is.read(bytes)) >= 0) {
                os.write(bytes, 0, length);
            }
        } finally {
            os.close();
            is.close();
        }
    }
}
