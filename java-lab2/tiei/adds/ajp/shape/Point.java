package tiei.ajp.shape;

public class Point extends Shape{
    private double x, y;

    public Point() {
        this("Point",0,0);
    }

    public Point(double x, double y) {
        this("Point", x, y);
    }

    public Point(String name, String color, double x, double y){
        super(name, color);
        this.x = x;
        this.y = y;
    }

    public Point(String name, double x, double y){
        super(name);
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }


    @Override
    public Shape translated(double dx, double dy) {
        return new Point(getName(), x + dx, y + dy);
    }

    @Override
    public Shape mirror(Point center) {
        double newX = 2 * center.x - x;
        double newY = 2 * center.y - y;
        return new Point(getName(), newX, newY);
    }

    @Override
    public Shape xMirror(double a) {
        double newY = 2 * a - y;
        return new Point(getName(), x, newY);
    }

    @Override
    public Shape yMirror(double a) {
        double newX = 2 * a - x;
        return new Point(getName(), newX, y);
    }

    @Override
    public void draw(WhiteBoard wb) {
        wb.setColor(toColor(getColor()));
        wb.drawPoint(x, y);
        wb.drawString(getName(), x, y + 0.2);
    }

}
