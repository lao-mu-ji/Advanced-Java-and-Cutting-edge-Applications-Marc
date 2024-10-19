package tiei.ajp.huffman;

import java.io.*;

public class BitOutputStream implements AutoCloseable {
    private OutputStream os;
    private byte[] currentByte;
    private int bitsInCurrentByte;

    public BitOutputStream(OutputStream os) {
        this.os = os;
        this.currentByte = new byte[1];
        this.bitsInCurrentByte = 0;
    }

    public void writeBit(boolean bit) throws IOException {
        currentByte[0] = (byte) (currentByte[0] << 1);
        currentByte[0] |= bit ? 1 : 0;
        bitsInCurrentByte++;
        if (bitsInCurrentByte == 8) {
            os.write(currentByte[0]);
            currentByte[0] = 0;
            bitsInCurrentByte = 0;
        }
    }

//    public void writeByte(int value) throws IOException {
//        byte valueByte = (byte) value;
//        for (int i = 0; i < 8; i++) {
//            writeBit((valueByte & (1 << i)) != 0);
//        }
//    }

    public void writeByte(byte value) throws IOException {
        os.write(value);
    }

    public void writePaddingBits() throws IOException {
        while (bitsInCurrentByte != 0) {
            writeBit(false);
        }
    }

    public void writeInt(long value) throws IOException {
        if (value < 0) {
            throw new IOException("Negative values are not supported.");
        }
        byte[] length = new byte[1];
        length[0] = 0;
        long temp = value;
        while (temp > 0) {
            temp >>= 8;
            length[0]++;
        }
        os.write(length[0]);
        for (int i = length[0] - 1; i >= 0; i--) {
            byte[] res = new byte[1];
            res[0] = 0;
            long l = value % 256;
            for (long i1 = 0; i1 < l; i1++){
                res[0] ++;
            }
            value /= 256;
            os.write(res[0]);
        }
    }

    @Override
    public void close() throws IOException {
        writePaddingBits();
        os.close();
    }
}

