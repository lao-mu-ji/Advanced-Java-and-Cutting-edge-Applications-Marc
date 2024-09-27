package tiei.ajp.shape;

public class Square extends Shape{
    private String name;
    private Point topLeft;
    private double sideLength;

    public Square(Point topLeft, double sideLength){
        this("Square", topLeft, sideLength);
    }

    public Square(String color, Point topLeft, double sideLength) {
        super("", color);
        this.topLeft = topLeft;
        this.sideLength = sideLength;
    }

    public String getName() {
        return name;
    }

    public Point getTopLeft() {
        return topLeft;
    }

    public double getSideLength() {
        return sideLength;
    }


    public Square translated(double dx, double dy) {
        Point newTopLeft = new Point("", topLeft.getX() + dx, topLeft.getY() + dy);
        return new Square(name, newTopLeft, sideLength);
    }

    public Square xMirror(double axisY) {
        double newY = 2 * axisY - topLeft.getY();
        Point newTopLeft = new Point("", topLeft.getX(), newY + sideLength);
        return new Square(name, newTopLeft, sideLength);
    }

    public Square yMirror(double axisX) {
        double newX = 2 * axisX - topLeft.getX();
        Point newTopLeft = new Point("", newX - sideLength, topLeft.getY());
        return new Square(name, newTopLeft, sideLength);
    }

    @Override
    public void draw(WhiteBoard wb) {
        wb.setColor(toColor(getColor()));
        wb.drawLine(topLeft.getX(), topLeft.getY(), topLeft.getX() + sideLength, topLeft.getY());
        wb.drawLine(topLeft.getX() + sideLength, topLeft.getY(), topLeft.getX() + sideLength, topLeft.getY() - sideLength);
        wb.drawLine(topLeft.getX() + sideLength, topLeft.getY() - sideLength, topLeft.getX(), topLeft.getY() - sideLength);
        wb.drawLine(topLeft.getX(), topLeft.getY() - sideLength, topLeft.getX(), topLeft.getY());
    }

    public Square mirror(Point mirrorPoint) {
        double newX = 2 * mirrorPoint.getX() - topLeft.getX();
        double newY = 2 * mirrorPoint.getY() - topLeft.getY();
        Point newTopLeft = new Point(newX - sideLength, newY + sideLength);

        return new Square(newTopLeft , sideLength);
    }
}
