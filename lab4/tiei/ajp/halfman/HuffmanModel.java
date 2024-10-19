//
// Advanced Java Programming
// TIEI - Fall 2024
//
package tiei.ajp.huffman;

import java.io.*;
import java.beans.*;

/**
 * This is the MODEL class for your project (MVC architecture)
 * YOU MUST NOT CHANGE THIS FILE
 */
public class HuffmanModel {
	
	private PropertyChangeSupport support;
	
	/**
	 * Creates a HuffmanModel instance
	 */
	public HuffmanModel() {
		support = new PropertyChangeSupport(this);
	}
	
	public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }
	
	/**
	 * Compress the file 'inputFile'
	 */
	public void compress(File inputFile) {
		try {
			String outputPath = inputFile.getAbsolutePath() + "." + Static.FILE_EXT;
			File outputFile = new File(outputPath);
			CompressFile cf = new CompressFile(inputFile, outputFile);
			cf.compress();
			support.firePropertyChange("msg", null, inputFile.getName() + " has been compressed");
		} catch (IOException ioe) {
			support.firePropertyChange("msg", null, inputFile.getName() + ": " + ioe.getMessage());
		}
	}
	
	/**
	 * Uncompress the file 'inputFile'
	 */
	public void uncompress(File inputFile) {
		try {
			String inputPath = inputFile.getAbsolutePath();
			String outputPath = inputPath.substring(0, inputPath.lastIndexOf('.'));
			File outputFile = new File(outputPath);
			UncompressFile uf = new UncompressFile(inputFile,outputFile);
			uf.uncompress();
			support.firePropertyChange("msg",null,inputFile.getName() + " has been uncompressed");
		} catch ( IOException ioe ) {
			support.firePropertyChange("msg", null, inputFile.getName() + ": " + ioe.getMessage());
		}
	}
}
