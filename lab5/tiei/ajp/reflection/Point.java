package tiei.ajp.reflection;

public class Point {
	
	private String name;
	private double x;
	private double y;

	public Point(String name, double x, double y) {
		super();
		this.name = name;
		this.x = x;
		this.y = y;
	}
	
	public Point() {
		this("O",0,0);
	}

	public String getName() {
		return name;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public double distance() {
		return Math.sqrt(Math.pow(x, 2)+Math.pow(y, 2));
	}
	
	public double distance(Point p) {
		return Math.sqrt(Math.pow(x-p.x, 2)+Math.pow(y-p.y, 2));
	}
	
	public void translate(double dx, double dy) {
		x += dx;
		y += dy;
	}
	
	@Override
	public String toString() {
		return name + "(" + x + "," + y + ")";
	}
}
