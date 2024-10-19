package tiei.ajp.huffman;

import java.io.*;

public class BitInputStream implements AutoCloseable {
    private InputStream is;
    private byte[] currentByte;
    private int bitsRemaining;


    public BitInputStream(InputStream is) {
        this.is = is;
        this.currentByte = new byte[1];
        this.bitsRemaining = 0;
    }

    public boolean readBit() throws IOException {
        if (bitsRemaining == 0) {
            if(is.read(currentByte) == -1){
                throw new EOFException("error-file-corrupted");
            };

            bitsRemaining = 8;
        }
        boolean bit = ((Byte.toUnsignedInt(currentByte[0]) >> --bitsRemaining) & 1) == 1;
        return bit;
    }

    public byte readByte() throws IOException {
        byte[] bytes = new byte[1];
        is.read(bytes);
        return bytes[0];
    }

    public long readInt() throws IOException {
//        long length = is.read() & 0xffffffffL;;
//        if (length == -1) {
//            throw new EOFException("End of file reached.");
//        }
        byte[] lengthByte = new byte[1];
        is.read(lengthByte);

        long value = 0;
        for (int i = 0; i < Byte.toUnsignedInt(lengthByte[0]); i++) {
            byte[] nextByte = new byte[1];
            is.read(nextByte);
//            System.out.println(Byte.toUnsignedInt(nextByte[0]));
//            int nextByte = is.read() & 0xff;

//            value = (value << 8) | nextByte;
            value += Byte.toUnsignedInt(nextByte[0]) << (i * 8);
        }
        return value;
    }

    @Override
    public void close() throws IOException {
        is.close();
    }
}