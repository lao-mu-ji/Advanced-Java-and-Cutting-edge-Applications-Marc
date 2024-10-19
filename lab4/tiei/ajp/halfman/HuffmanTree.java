package tiei.ajp.huffman;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanTree {
    private Node root;

    public Node getRoot() {
        return this.root;
    }

    public HuffmanTree(){
    }

    public HuffmanTree(Map<Byte, Integer> frequencyMap) {
        this.root = buildTree(frequencyMap);
    }

    private Node buildTree(Map<Byte, Integer> frequencyMap) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        for (Map.Entry<Byte, Integer> entry : frequencyMap.entrySet()) {
            queue.add(new Node(entry.getKey(), entry.getValue()));
        }

        while (queue.size() > 1) {
            Node left = queue.poll();
            Node right = queue.poll();
            Node parent = new Node(null, left.frequency + right.frequency, left, right);
            queue.add(parent);
        }

        return queue.poll();
    }

    public Map<Byte, String> buildEncodingMap() {
        Map<Byte, String> encodingMap = new HashMap<>();
        buildEncodingMap(root, "", encodingMap);
        return encodingMap;
    }

    private void buildEncodingMap(Node node, String code, Map<Byte, String> encodingMap) {
        if (node.isLeaf()) {
            encodingMap.put(node.byteValue, code);
            return;
        }
        buildEncodingMap(node.left, code + '0', encodingMap);
        buildEncodingMap(node.right, code + '1', encodingMap);
    }

    public void writeTree(BitOutputStream bos) throws IOException {
        writeTree(root, bos);
    }

    private void writeTree(Node node, BitOutputStream bos) throws IOException {
        if (node.isLeaf()) {
            byte[] temp = new byte[1];
            temp[0] = 0x00;
            bos.writeByte(temp[0]);
            bos.writeByte(node.byteValue);
        } else {
            byte[] temp = new byte[1];
            temp[0] = 0x01;
            bos.writeByte(temp[0]);
            writeTree(node.left, bos);
            writeTree(node.right, bos);
        }
    }

    public void readTree(BitInputStream bis) throws IOException {
        root = readHuffTree(bis);
    }

    public Node readHuffTree(BitInputStream bis) throws IOException {
        int isLeaf = Byte.toUnsignedInt(bis.readByte());
        if(isLeaf == 0){
            return new Node((byte)bis.readByte(), 0);
        } else if(isLeaf == 1){
            Node left = readHuffTree(bis);
            Node right = readHuffTree(bis);
            if (left == null || right == null) {
                throw new IOException("Error: Invalid Huffman tree structure.");
            }
            return new Node(null, left.frequency + right.frequency, left, right);
        } else {
            throw new IOException("Error: Invalid Huffman tree format.");
        }
    }

    // Getters and other helper methods

    static class Node implements Comparable<Node> {
        Byte byteValue;
        int frequency;
        Node left;
        Node right;

        Node(Byte byteValue, int frequency) {
            this.byteValue = byteValue;
            this.frequency = frequency;
        }

        Node(Byte byteValue, int frequency, Node left, Node right) {
            this.byteValue = byteValue;
            this.frequency = frequency;
            this.left = left;
            this.right = right;
        }

        boolean isLeaf() {
            return left == null && right == null;
        }

        @Override
        public int compareTo(Node o) {
            return this.frequency - o.frequency;
        }
    }
}
