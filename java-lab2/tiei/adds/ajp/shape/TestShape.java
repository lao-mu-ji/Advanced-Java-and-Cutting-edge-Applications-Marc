//
// Advanced Java Programming
// TIEI - Fall 2024
//
package tiei.ajp.shape;

/**
 * A class to test your code
 */
public class TestShape {

	// you can adjust this delay
	private static final long delay = 1500;

	public static void main(String[] args) throws InterruptedException  {

		WhiteBoard wb = new WhiteBoard();

		print("origin");

		Point origin = new Point();
		origin.draw(wb);


		print("a point");
		Point p1 = new Point(3.5,3.5);
		p1.draw(wb);


		print("a point");
		Point p2 = new Point(3.5,-1.5);
		p2.draw(wb);


		print("a line");
		Line l1 = new Line(origin,p1);
		l1.draw(wb);


		print("a point");
		Point p3 = new Point(-8.0,4.5);
		p3.draw(wb);


		print("a rectangle");
		Rectangle r1 = new Rectangle(p3,3,2);
		r1.draw(wb);


		print("a square");
		Square s1 = new Square(new Point(7,7),2);
		s1.draw(wb);


		print("a point");
		Point p4 = new Point(-3.5,-3.5);
		p4.draw(wb);


		print("a point");
		Point p5 = new Point(-5,-1.5);
		p5.draw(wb);


		print("a point");
		Point p6 = new Point(-4.5,-5);
		p6.draw(wb);


		print("a triangle");
		Triangle t1 = new Triangle(p4,p5,p6);
		t1.draw(wb);


		print("a circle");
		Circle c1 = new Circle(new Point(-2,0),1.5);
		c1.draw(wb);


		print("translaton of the circle");
		Shape c2 = c1.translated(1.5,4);
		c2.draw(wb);


		print("x-mirror of a point");
		Shape p7 = p2.xMirror(0);
		p7.draw(wb);


		print("mirror of a rectangle");
		Rectangle r2 = r1.mirror(origin);
		r2.draw(wb);


		print("y-mirror of a triangle");
		Shape t2 = t1.yMirror(0);
		t2.draw(wb);
	}

	private static void print(String s) throws InterruptedException {
		Thread.sleep(delay);
		System.out.println(s);
		Thread.sleep(delay/2);
	}
}
