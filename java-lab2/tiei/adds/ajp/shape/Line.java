package tiei.ajp.shape;

public class Line extends Shape {
    private Point start;
    private Point end;

    public Line(Point start, Point end){
        this("Red", start, end);
    }

    public Line( String color, Point start, Point end){
        super("", color);
        this.start = start;
        this.end = end;
    }

//    public Line(String name, Point start, Point end) {
//        super(name);
//        this.start = start;
//        this.end = end;
//    }


    @Override
    public Shape translated(double dx, double dy) {
        Point newStart = new Point(start.getName(), start.getX() + dx, start.getY() + dy);
        Point newEnd = new Point(end.getName(), end.getX() + dx, end.getY() + dy);
        return new Line(getName(), newStart, newEnd);
    }

    @Override
    public Shape mirror(Point center) {
        Point newStart = (Point) start.mirror(center);
        Point newEnd = (Point) end.mirror(center);
        return new Line(getName(), newStart, newEnd);
    }

    @Override
    public Shape xMirror(double a) {
        Point newStart = (Point) start.xMirror(a);
        Point newEnd = (Point) end.xMirror(a);
        return new Line(getName(), newStart, newEnd);
    }

    @Override
    public Shape yMirror(double a) {
        Point newStart = (Point) start.yMirror(a);
        Point newEnd = (Point) end.yMirror(a);
        return new Line(getName(), newStart, newEnd);
    }

    @Override
    public void draw(WhiteBoard wb) {
        wb.setColor(toColor(getColor()));
        System.out.println(getColor());
        wb.drawLine(start.getX(), start.getY(), end.getX(), end.getY());
    }
}
