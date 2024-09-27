package tiei.ajp.shape;

public class Triangle extends Shape {
    private Point vertex1;
    private Point vertex2;
    private Point vertex3;

    public Triangle(Point vertex1, Point vertex2, Point vertex3){
        this("Triangle", vertex1, vertex2, vertex3);
    }

    public Triangle(String color, Point vertex1, Point vertex2, Point vertex3) {
        super("", color);
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.vertex3 = vertex3;
    }


    @Override
    public Shape translated(double dx, double dy) {
        Point newVertex1 = (Point) vertex1.translated(dx, dy);
        Point newVertex2 = (Point) vertex2.translated(dx, dy);
        Point newVertex3 = (Point) vertex3.translated(dx, dy);
        return new Triangle(getName(), newVertex1, newVertex2, newVertex3);
    }

    @Override
    public Shape mirror(Point center) {
        Point newVertex1 = (Point) vertex1.mirror(center);
        Point newVertex2 = (Point) vertex2.mirror(center);
        Point newVertex3 = (Point) vertex3.mirror(center);
        return new Triangle(getName(), newVertex1, newVertex2, newVertex3);
    }

    @Override
    public Shape xMirror(double a) {
        Point newVertex1 = (Point) vertex1.xMirror(a);
        Point newVertex2 = (Point) vertex2.xMirror(a);
        Point newVertex3 = (Point) vertex3.xMirror(a);
        return new Triangle(getName(), newVertex1, newVertex2, newVertex3);
    }

    @Override
    public Shape yMirror(double a) {
        Point newVertex1 = (Point) vertex1.yMirror(a);
        Point newVertex2 = (Point) vertex2.yMirror(a);
        Point newVertex3 = (Point) vertex3.yMirror(a);
        return new Triangle(getName(), newVertex1, newVertex2, newVertex3);
    }

    @Override
    public void draw(WhiteBoard wb) {
        wb.drawLine(vertex1.getX(), vertex1.getY(), vertex2.getX(), vertex2.getY());
        wb.drawLine(vertex2.getX(), vertex2.getY(), vertex3.getX(), vertex3.getY());
        wb.drawLine(vertex3.getX(), vertex3.getY(), vertex1.getX(), vertex1.getY());
    }
}
