package tiei.ajp.shape;

public class Rectangle extends Shape {
    private Point upperLeft;
    private double width;
    private double height;

    public Rectangle(Point upperLeft, double width, double height){
        this("Rectangle", upperLeft, width, height);
    }

    public Rectangle(String color, Point upperLeft, double width, double height) {
        super("", color);
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }


    @Override
    public Rectangle translated(double dx, double dy) {
        Point newUpperLeft = new Point(upperLeft.getName(), upperLeft.getX() + dx, upperLeft.getY() + dy);
        return new Rectangle(getName(), newUpperLeft, width, height);
    }

    @Override
    public Rectangle mirror(Point center) {
        Point newUpperLeft = (Point) upperLeft.mirror(center);
//        double dx = upperLeft.getX() - newUpperLeft.getX();
//        double dy = upperLeft.getY() - newUpperLeft.getY();
        newUpperLeft = new Point(newUpperLeft.getName(), newUpperLeft.getX() - width, newUpperLeft.getY() + height);
        return new Rectangle(getName(), newUpperLeft, width, height);
    }

    @Override
    public Rectangle xMirror(double a) {
        Point newUpperLeft = (Point) upperLeft.xMirror(a);
//        double dy = upperLeft.getY() - newUpperLeft.getY();
        newUpperLeft = new Point(newUpperLeft.getName(), newUpperLeft.getX(), newUpperLeft.getY() + height);
        return new Rectangle(getName(), newUpperLeft, width, height);
    }

    @Override
    public Rectangle yMirror(double a) {
        Point newUpperLeft = (Point) upperLeft.yMirror(a);
//        double dx = upperLeft.getX() - newUpperLeft.getX();
        newUpperLeft = new Point(newUpperLeft.getName(), newUpperLeft.getX() - width, newUpperLeft.getY());
        return new Rectangle(getName(), newUpperLeft, width, height);
    }

    @Override
    public void draw(WhiteBoard wb) {
        wb.setColor(toColor(getColor()));
        wb.drawLine(upperLeft.getX(), upperLeft.getY(), upperLeft.getX() + width, upperLeft.getY());
        wb.drawLine(upperLeft.getX() + width, upperLeft.getY(), upperLeft.getX() + width, upperLeft.getY() - height);
        wb.drawLine(upperLeft.getX() + width, upperLeft.getY() - height, upperLeft.getX(), upperLeft.getY() - height);
        wb.drawLine(upperLeft.getX(), upperLeft.getY() - height, upperLeft.getX(), upperLeft.getY());
    }
}
