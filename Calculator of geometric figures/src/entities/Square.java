package entities;

public class Square {
    private double side;

    private double area;

    public Square() {
        super();
    }

    public Square(double radius) {
        this.side = radius;
    }

    public double getSide() {
        return side;
    }

    public void setSide(double side) {
        this.side = side;
    }
    public double areaFigureSquare(){
        area = side * side;
        return area;
    }
}
