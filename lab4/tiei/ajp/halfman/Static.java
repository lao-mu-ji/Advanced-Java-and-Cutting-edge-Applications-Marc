//
// Advanced Java Programming
// TIEI - Fall 2024
//
package tiei.ajp.huffman;

/**
 * A class for all the constants
 */
public class Static {

    public static final int FULL_BYTE  = 256; // the number of bytes
    public static final int HALF_BYTE  = 128; // half the number of bytes
    public static final int BYTE_SIZE  =   8; // the size (in bits) of a byte

    public static final int LEAF_BYTE  =   0; // the special byte to mark a leaf in the Huffman tree
    public static final int NODE_BYTE  =   1; // the special byte to mark a node in the Huffman tree
    
    public static final String FILE_EXT = "hu"; // the file extension for the compressed files

    public static final long MAGIC_NUMBER = 123456789; // the magic number
}
