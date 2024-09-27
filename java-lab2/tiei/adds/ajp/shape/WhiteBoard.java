//
// Advanced Java Programming
// TIEI - Fall 2024
//
package tiei.ajp.shape;

import java.awt.*;

/**
 * A class to simulate a white board
 * A small main is provided to demo the methods
 */
public class WhiteBoard {
	
	private int width;
	private int height;
	private int unit;
	private int pointSize;
	
	private DrawingPanel dp;
	private Graphics2D g;
	
	public WhiteBoard() {
		width = 1200;
		height = 900;
		unit = 50;
		pointSize = 8;
		// previousColor = Color.BLACK;
		dp = new DrawingPanel(width,height);
		dp.setBackground(Color.WHITE);
		g  = (Graphics2D) dp.getGraphics();
		drawCoordinates();
	}
	
	public void setColor(Color c) {
		g.setColor(c);
	}
		
	public void drawPoint(double x, double y) {
		int xx = xPixels(x);
		int yy = yPixels(y);
		g.fillOval(xx - pointSize/2, yy - pointSize/2, pointSize, pointSize);
	}
	
	public void drawLine(double x1, double y1, double x2, double y2) {
		int xx1 = xPixels(x1);
		int yy1 = yPixels(y1);
		int xx2 = xPixels(x2);
		int yy2 = yPixels(y2);
		g.drawLine(xx1, yy1, xx2, yy2);
	}
	
	public void drawCircle(double x, double y, double radius) {
		int xx = xPixels(x);
		int yy = yPixels(y);
		int rr = (int) Math.round(radius * unit);
		g.drawOval(xx - rr, yy - rr, 2*rr, 2*rr);
	}
	
	public void drawString(String s, double x, double y) {
		int xx = xPixels(x);
		int yy = yPixels(y);
		g.drawString(s, xx + pointSize, yy - pointSize);
	}
		
	private void drawCoordinates() {
		g.drawLine(width/2, 0, width/2, height);
		g.drawLine(0, height/2, width, height/2);
		for ( int x = unit; x < width; x += unit ) {
			g.drawLine(x, height/2, x, height/2 - 10);
		}
		for ( int y = unit; y < height; y += unit ) {
			g.drawLine(width/2, y, width/2 + 10, y);
		}
	}
	
	private int xPixels(double x) {
		return (int) Math.round(width/2 + x * unit);
	}
	
	private int yPixels(double y) {
		return (int) Math.round(height/2 - y * unit);
	}
	
	public static void main(String[] args) throws InterruptedException  {
		
		WhiteBoard wb = new WhiteBoard();
		
		wb.drawPoint(0, 0);
		Thread.sleep(1000);
		
		wb.setColor(Color.RED);
		wb.drawPoint(-2, 5);
		Thread.sleep(1000);
		
		wb.setColor(Color.BLUE);
		wb.drawString("A", -2, 5);
		Thread.sleep(1000);
		
		wb.setColor(Color.GREEN);
		wb.drawPoint(4, 5);
		Thread.sleep(1000);
		
		wb.setColor(Color.ORANGE);
		wb.drawLine(-4, -2, 1, 2);
		Thread.sleep(1000);
		
		wb.setColor(Color.YELLOW);
		wb.drawLine(-2, 1, 5, 3);
		Thread.sleep(1000);
		
		wb.setColor(Color.BLACK);
		wb.drawCircle(4, -3, 2);
	}
}
