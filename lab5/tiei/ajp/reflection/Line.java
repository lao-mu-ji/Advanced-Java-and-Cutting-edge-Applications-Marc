package tiei.ajp.reflection;

public class Line {
	
	private String name;
	private Point start;
	private Point end;
	
	public Line(String name, Point start, Point end) {
		this.name = name;
		this.start = start;
		this.end = end;
	}

	public String getName() {
		return name;
	}

	public Point getStart() {
		return start;
	}

	public Point getEnd() {
		return end;
	}
	
	public double length() {
		return start.distance(end);
	}
	
	public void translate(double dx, double dy) {
		start.translate(dx, dy);
		end.translate(dx, dy);
	}
	
	@Override
	public String toString() {
		return name + "[" + start + "," + end + "]";
	}

}
