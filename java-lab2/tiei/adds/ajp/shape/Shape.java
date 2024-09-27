package tiei.ajp.shape;

import java.awt.*;

public abstract class Shape {
    public String name;
    public String color;
    private static final String[] PREDEFINED_COLORS = {"Red", "Green", "Blue", "Purple", "Orange"};

    public Color toColor(String co) {
        Color color = null;
        switch (co){
            case "Red":
                color = Color.RED;
                break;
            case "Green":
                color = Color.GREEN;
                break;
            case "Blue":
                color = Color.BLUE;
                break;
            case "Purple":
                color = new Color(0x9370DB);
                break;

            default:
                color = Color.ORANGE;
        }
        return color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }


    public Shape(String name) {
        this.name = name;
        this.color = PREDEFINED_COLORS[(int) (Math.random() * PREDEFINED_COLORS.length)];
    }

    public Shape(String name, String color) {
        this.name = name;
        this.color = color;
    }




    public abstract Shape translated(double dx, double dy);

    public abstract Shape mirror(Point center);

    public abstract Shape xMirror(double a);

    public abstract Shape yMirror(double a);

    public abstract void draw(WhiteBoard wb);
}
