package entities;

public abstract class Data_Figure {

    private double base;
    private double height;

    private double area;

    public Data_Figure() {

    }

    public Data_Figure(double base, double height) {
        this.base = base;
        this.height = height;
    }

    public double getBase() {
        return base;
    }

    public double getHeight() {
        return height;
    }

    public void setBase(double base) {
        this.base = base;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double areaFigure(){
        return area = base * height;
    }

    public abstract double areaFigure(double base, double height);
}
