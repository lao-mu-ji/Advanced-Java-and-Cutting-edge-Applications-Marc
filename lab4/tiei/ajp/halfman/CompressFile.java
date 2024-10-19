//
// Advanced Java Programming
// TIEI - Fall 2024
//
package tiei.ajp.huffman;

import java.io.*;
import java.util.*;

/**
 * A class to compress a file
 */
public class CompressFile {
    private File inFile;
    private File outFile;
    private Map<Byte, Integer> frequencyMap;

//    private long numberOfData = 0;



    /**
     * Return an CompressFile object to compress 'inFile' into 'outFile'
     */
    public CompressFile(File inFile, File outFile) throws IOException {
        this.inFile = inFile;
        this.outFile = outFile;
        this.frequencyMap = new HashMap<>();
    }

    /**
     * Compress the file
     */
    public void compress() throws IOException {
        buildFrequencyMap();
        HuffmanTree huffmanTree = new HuffmanTree(frequencyMap);
        Map<Byte, String> encodingMap = huffmanTree.buildEncodingMap();

        try (BitOutputStream bos = new BitOutputStream(new FileOutputStream(outFile))) {
            writeMagicNumber(bos);
            huffmanTree.writeTree(bos);
            writeNumberOfData(bos, count(encodingMap));
            writeCompressedData(bos, encodingMap);
            bos.writePaddingBits();
//            bos.writeInt(huffmanTree.getTotalBits());
        }
    }

    private void buildFrequencyMap() throws IOException {
        try (InputStream is = new BufferedInputStream(new FileInputStream(inFile))) {
            int byteRead;
            while ((byteRead = is.read()) != -1) {
                frequencyMap.put((byte) byteRead, frequencyMap.getOrDefault((byte) byteRead, 0) + 1);
            }
        }
    }

    private void writeMagicNumber(BitOutputStream bos) throws IOException {
        bos.writeInt(Static.MAGIC_NUMBER);
    }

    private void writeNumberOfData(BitOutputStream bos, long numberOfData) throws IOException {
        bos.writeInt(numberOfData);
    }

    private long count(Map<Byte, String> encodingMap) throws IOException {
        long numberOfData = 0;
        try (InputStream is = new BufferedInputStream(new FileInputStream(inFile))) {
            int byteRead;
            while ((byteRead = is.read()) != -1) {
                String code = encodingMap.get((byte) byteRead);
                numberOfData += code.length();
            }
        }
        return numberOfData;
    }

    private void writeCompressedData(BitOutputStream bos, Map<Byte, String> encodingMap) throws IOException {
        try (InputStream is = new BufferedInputStream(new FileInputStream(inFile))) {
            byte[] byteRead = new byte[1];
            while (is.read(byteRead) != -1) {
                String code = encodingMap.get(byteRead[0]);
                for (char bit : code.toCharArray()) {
                    bos.writeBit(bit == '1' ? true : false);
                }
            }
        }
    }
}