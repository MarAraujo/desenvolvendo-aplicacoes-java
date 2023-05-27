package entientes;

public class CirculoColorido extends Circulo {

    private  String corTracado;
    private String corInterna;

    public CirculoColorido(double x, double y, double r, String corTracado, String corInterna) {
        super(x, y, r);
        this.corTracado = corTracado;
        this.corInterna = corInterna;
    }

    public void setCorTracado(String corTracado) {
        this.corTracado = corTracado;
    }

    public void setCorInterna(String corInterna) {
        this.corInterna = corInterna;
    }

    public String getCorTracado() {
        return corTracado;
    }

    public String getCorInterna() {
        return corInterna;
    }

    @Override
    public String toString(){
        String str = "Cor traçado: " + corTracado + " | Cor interna: " + corInterna + "\n";
        str += "coord Centro: (" + getX() + "," + getY() + ")\n";
        str += "Raio: " + getRaio();
        return str;
    }


}
