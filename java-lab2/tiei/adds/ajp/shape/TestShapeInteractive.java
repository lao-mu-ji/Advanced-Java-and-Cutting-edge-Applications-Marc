package tiei.ajp.shape;

import java.util.*;

/**
 * A class to test your code interactively
 */
public class TestShapeInteractive {
	
	private static final String[] actions = { "exit", "show", "create", "translate", "mirror", "xmirror", "ymirror" };
	private static final String[] shapes  = { "point", "line", "rectangle", "square", "triangle", "circle" };
	private static Map<String,Shape> table = new TreeMap<>();
	
	///////////////////////////////////////////////////////////////////////////
	////
	//// Each line ending by the comment "YOUR CODE" contains some of your code
	////
	///////////////////////////////////////////////////////////////////////////
	
	private static void showShape(Scanner input) {
		Shape s = selectShape(input);
		System.out.println(s);  // YOUR CODE
	}
	
	private static void createPoint(Scanner input, WhiteBoard wb) {
		String name = readString("enter name: ",input);
		String color = readString("enter color: ",input);
		double x = readDouble("enter x coordinate: ",input);
		double y = readDouble("enter y coordinate: ",input);
		Point p = new Point(name, color,x,y); // YOUR CODE
		storeAndDraw(p,wb);
	}

	private static String readString(String prompt, Scanner input) {
		System.out.print(prompt);
		return input.nextLine();
	}


	private static void createLine(Scanner input, WhiteBoard wb) {
//		String name = readString("enter name: ",input);
		String color = readString("enter color: ",input);
		Point p1 = selectPoint("select the first point: ",input);
		Point p2 = selectPoint("select the second point: ",input);
		Line l = new Line(color,p1,p2);  // YOUR CODE
		storeAndDraw(l,wb);
	}
	
	private static void createRectangle(Scanner input, WhiteBoard wb) {
		String color = readString("enter color: ",input);
		Point p = selectPoint("select the point (upper-left corner): ",input);
		double w = readDouble("enter the width: ",input);
		double h = readDouble("enter the height: ",input);
		Rectangle r = new Rectangle(color, p,w,h);  // YOUR CODE
		storeAndDraw(r,wb);
	}
	
	private static void createSquare(Scanner input, WhiteBoard wb) {
		String color = readString("enter color: ",input);
		Point p = selectPoint("select the point (upper-left corner): ",input);
		double w = readDouble("enter the width: ",input);
		Square s = new Square(color, p,w);  // YOUR CODE
		storeAndDraw(s,wb);
	}
	
	private static void createCircle(Scanner input, WhiteBoard wb) {
		String color = readString("enter color: ",input);
		Point p = selectPoint("select the point (center): ",input);
		double r = readDouble("enter the radius: ",input);
		Circle c = new Circle(color, p,r);  // YOUR CODE
		storeAndDraw(c,wb);
	}
	
	private static void createTriangle(Scanner input, WhiteBoard wb) {
		String color = readString("enter color: ",input);
		Point p1 = selectPoint("select the first point: ",input);
		Point p2 = selectPoint("select the second point: ",input);
		Point p3 = selectPoint("select the third point: ",input);
		Triangle t = new Triangle(color, p1,p2,p3);  // YOUR CODE
		storeAndDraw(t,wb);
	}
	
	private static void doTranslate(Scanner input, WhiteBoard wb) {
		Shape s = selectShape(input);
		double dx = readDouble("enter dx: ",input);
		double dy = readDouble("enter dy: ",input);
		Shape t = s.translated(dx, dy);  // YOUR CODE
		storeAndDraw(t,wb);
	}
	
	private static void doMirror(Scanner input, WhiteBoard wb) {
		Shape s = selectShape(input);
		Point p = selectPoint("select the point: ",input);
		Shape m = s.mirror(p);  // YOUR CODE
		storeAndDraw(m,wb);
	}
	
	private static void doXmirror(Scanner input, WhiteBoard wb) {
		Shape s = selectShape(input);
		double y = readDouble("enter a (y = a): ",input);
		Shape m = s.xMirror(y);  // YOUR CODE
		storeAndDraw(m,wb);
	}
	
	private static void doYmirror(Scanner input, WhiteBoard wb) {
		Shape s = selectShape(input);
		double x = readDouble("enter a (x = a): ",input);
		Shape m = s.yMirror(x);  // YOUR CODE
		storeAndDraw(m,wb);
	}
	
	private static void storeAndDraw(Shape s, WhiteBoard wb) {
		table.put(s.getName().toLowerCase(),s);
		s.draw(wb);  // YOUR CODE
	}

	public static void main(String[] args) {
		WhiteBoard wb = new WhiteBoard();
		Point origin = new Point(); // YOUR CODE
		storeAndDraw(origin,wb);
		Scanner console = new Scanner(System.in);
		System.out.println("Welcome to the Shape Tester\n");
		while ( true ) {
			String action = menu(actions,"> ",console);
			if ( action.equals("exit") )
				break;
			doAction(action, console,wb);
		}
		System.out.println("\nThank you for using the Shape Tester");
	}
	
	///////////////////////////////////////////////////////////////////////////
	////
	//// The code below DOES NOT use your code
	////
	///////////////////////////////////////////////////////////////////////////
	
	private static String menu(String[] options, String prompt, Scanner input) {
		while ( true ) {
			System.out.print(prompt);
			int index = 1;
			for ( String option : options ) {
				System.out.print(option + "(" + index++ + ") ");
			}
			System.out.println(": ");
			String choice = input.nextLine().trim();
			try {
				index = Integer.parseInt(choice) - 1;
				if ( index >= 0 && index < options.length )
					return options[index];
			}
			catch ( Exception e) {}
		}
	}
	
	private static void doAction(String action, Scanner input, WhiteBoard wb) {
		if ( action.equals("show") )
			showShape(input);
		else if ( action.equals("create") )
			doCreate(input,wb);
		else if ( action.equals("translate") )
			doTranslate(input,wb);
		else if ( action.equals("mirror") ) 
			doMirror(input,wb);
		else if ( action.equals("xmirror") )
			doXmirror(input,wb);
		else
			doYmirror(input,wb);
	}
	
	private static void doCreate(Scanner input, WhiteBoard wb) {
		String shape = menu(shapes,">> ",input);
		if ( shape.equals("point") )
			createPoint(input,wb);
		else if ( shape.equals("line") )
			createLine(input,wb);
		else if ( shape.equals("rectangle") )
			createRectangle(input,wb);
		else if ( shape.equals("square") )
			createSquare(input,wb);
		else if ( shape.equals("triangle") )
			createTriangle(input,wb);
		else
			createCircle(input,wb);
	}
	
	
	private static Point selectPoint(String prompt, Scanner input) {
		while ( true ) {
			System.out.print(prompt);
			String name = input.nextLine().trim().toLowerCase();
			if ( table.containsKey(name) )
				return (Point) table.get(name);
			System.out.println(">> there is not point named " + name);
		}
	}
	
	private static Shape selectShape(Scanner input) {
		while ( true ) {
			System.out.print("enter the name of the shape: ");
			String name = input.nextLine().trim().toLowerCase();
			if ( table.containsKey(name) )
				return table.get(name);
			System.out.println(">> there is not shape named " + name);
		}
	}

	private static double readDouble(String prompt, Scanner input) {
		String d = null;
		while (true) {
			try {
				System.out.print(prompt);
				d = input.nextLine();
				return Double.parseDouble(d);
			}
			catch ( Exception e ) {
				System.out.println(">> " + d + " is not a double value");
			}
		}
	}
}
