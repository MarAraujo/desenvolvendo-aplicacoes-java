package entities;

public class Cicle {
    private double radius;
    private double area;

    public Cicle() {

    }
    public Cicle(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double areFigureCicle(){
        area = (radius * radius) * 3.141;
        return area;
    }
}
