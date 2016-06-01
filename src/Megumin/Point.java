package Megumin;

public class Point {
    private int x;
    private int y;

    public Point() {
        x = 0;
        y = 0;
    }

    public Point(Point p) {
        x = p.getX();
        y = p.getY();
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Point getPoint() {
        return this;
    }

    public void setPoint(Point p) {
        x = p.getX();
        y = p.getY();
    }

    public void setPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void offset(int dx, int dy) {
        x += dx;
        y += dy;
    }

    @Override
    public boolean equals(Object obj) {
        return ((Point)obj).getX() == x && ((Point)obj).getY() == y;
    }

    @Override
    public String toString() {
        return "{x = " + x + ", y = " + y + "}";
    }
}
