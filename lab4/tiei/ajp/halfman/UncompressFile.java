//
// Advanced Java Programming
// TIEI - Fall 2024
//
package tiei.ajp.huffman;

import java.util.*;
import java.io.*;

/**
 * A class to uncompress a file
 */
public class UncompressFile {

    private File inputFile;
    private File outputFile;

    /**
     * Return an UncompressFile object to uncompress 'inFile' into 'outFile'
     */
    public UncompressFile(File inputFile, File outputFile) throws IOException {
        this.inputFile = inputFile;
        this.outputFile = outputFile;
    }

    /**
     * Uncompress the file
     */
    public void uncompress() throws IOException {
        try (BitInputStream bis = new BitInputStream(new FileInputStream(inputFile))) {
            long magicNumber = bis.readInt();
            if (magicNumber != Static.MAGIC_NUMBER) {
                throw new IOException("Not a Huffman compressed file.");
            }

            HuffmanTree huffmanTree = new HuffmanTree();
            huffmanTree.readTree(bis);
//            HuffmanTree.Node root = huffmanTree.getRoot();
            long totalBits = bis.readInt();
            try (OutputStream os = new BufferedOutputStream(new FileOutputStream(outputFile))) {
                uncompressData(bis, os, huffmanTree, totalBits);
            }
        }
    }

    private void uncompressData(BitInputStream bis, OutputStream os, HuffmanTree huffmanTree, long totalBits) throws IOException {
        long bitsRead = 0;
        HuffmanTree.Node current = huffmanTree.getRoot();
        while (bitsRead < totalBits) {
            boolean bit = bis.readBit();
//            if
            if (bit) {
                current = current.right;
            } else {
                current = current.left;
            }

            if (current.isLeaf()) {
                os.write(current.byteValue);
                current = huffmanTree.getRoot();
            }
            bitsRead++;
        }
        if(bitsRead < totalBits){
            throw new IOException("Error: Not enough bits read.");
        }
    }
}