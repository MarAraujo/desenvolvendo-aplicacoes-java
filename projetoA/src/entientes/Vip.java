package entientes;

public class Vip extends Ingresso{

    private double adicional = 1.25;

    public Vip(double adicional) {
        this.adicional = adicional;
    }
    public double getAdicional() {
        return adicional;
    }

    public void setAdicional(double adicional) {
        this.adicional = adicional;
    }

    @Override
    public double valorFinal() {
        return getValor()*getAdicional();
    }
}
