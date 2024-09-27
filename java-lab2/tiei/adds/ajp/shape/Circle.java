package tiei.ajp.shape;

public class Circle extends Shape {
    private Point center;
    private double radius;

    public Circle(Point center, double radius){
        this("Circle", center, radius);
    }

    public Circle(String color, Point center, double radius) {
        super("", color);
        this.center = center;
        this.radius = radius;
    }

    public Point getCenter() {
        return center;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public Shape translated(double dx, double dy) {
        Point newCenter = (Point) center.translated(dx, dy);
        return new Circle(getName(), newCenter, radius);
    }

    @Override
    public Shape mirror(Point center) {
        Point newCenter = (Point) this.center.mirror(center);
        return new Circle(getName(), newCenter, radius);
    }

    @Override
    public Shape xMirror(double a) {
        Point newCenter = (Point) this.center.xMirror(a);
        return new Circle(getName(), newCenter, radius);
    }

    @Override
    public Shape yMirror(double a) {
        Point newCenter = (Point) this.center.yMirror(a);
        return new Circle(getName(), newCenter, radius);
    }

    @Override
    public void draw(WhiteBoard wb) {
        wb.setColor(toColor(color));
        wb.drawCircle(center.getX(), center.getY(), radius);
    }
}
