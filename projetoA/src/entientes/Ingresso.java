package entientes;

public abstract class Ingresso {

    private double valor = 100.50;
    private int categoria;

    public abstract double valorFinal();

    public double getValor() {
        return valor;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }
}
