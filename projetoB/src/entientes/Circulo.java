package entientes;

public class Circulo {
    private Ponto centro;
    private double raio;

    public Circulo(double x, double y, double r) {
        if(x < 0) x = 0;
        if(y < 0) y = 0;
        Ponto centro = new Ponto(x,y);
        if(r > 0) raio = r;
        else raio = 1;
    }
    public void mover(double x, double y){
        centro.setX(x);
        centro.setY(y);
    }
    public void aumentar(){
        raio++;
    }public void diminuir(){
        raio--;
    }
    public double getX(){
        return centro.getX();
    }public double getY(){
        return centro.getY();
    }
    public double getRaio(){
        return raio;
    }


}
