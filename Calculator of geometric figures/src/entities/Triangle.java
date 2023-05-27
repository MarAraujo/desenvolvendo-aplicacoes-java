package entities;

public class Triangle extends Data_Figure{

    private double area;
    public Triangle() {
    }

    public Triangle(double base, double height) {
        super(base, height);
    }

    @Override
    public double areaFigure(double base, double height){
        return area = (base * height)/2;
    }
}
