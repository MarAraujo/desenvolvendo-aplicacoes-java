import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SistemaInterno aux = new SistemaInterno();
        aux.pedirIngresso();
    }
}
class SistemaInterno {
    private final Scanner scanner = new Scanner(System.in);

    public void pedirIngresso() {
        System.out.println("Escolha o tipo de ingresso:\n 1#Normal\n 2#Vip Superior\n 3#Vip" +
                "Padrao\n");
        int tipoIngresso = 0;
        do {
            tipoIngresso = scanner.nextInt();
            if (tipoIngresso < 1 || tipoIngresso > 3) {
                System.out.println("Escolha invalida");
            }
        } while (tipoIngresso < 1 || tipoIngresso > 3);
        definirCategoria(TipoIngresso.values()[tipoIngresso - 1]);
    }

    private void definirCategoria(TipoIngresso tipoIngresso) {
        Ingresso ingresso;
        switch (tipoIngresso) {
            case NORMAL:
                ingresso = new Normal();
                break;
            case CAMAROTE_SUPERIOR:
                ingresso = new CamaroteSuperior();
                break;
            case CAMAROTE_INFERIOR:
                ingresso = new CamaroteInferior();
                break;
            default:
                ingresso = null;
                double valor = ingresso.valorFinal();
                System.out.println("O valor final do ingresso é: " + valor);
        }
    }

    enum TipoIngresso {
        NORMAL, CAMAROTE_SUPERIOR, CAMAROTE_INFERIOR
    }

    class CamaroteInferior extends Vip {
        public double valorFinal() {
            return super.valorFinal();
        }
    }

    class CamaroteSuperior extends Vip {
        private double adicionalSup = 1.50;

        public double valorFinal() {
            return super.valorFinal() * getAdicionalSup();
        }

        public double getAdicionalSup() {
            return this.adicionalSup;
        }

        public void setAdicionalSup(double adicional) {
            this.adicionalSup = adicional;
        }
    }

    class Normal extends Ingresso {
        public double valorFinal() {
            return getValor();
        }
    }

    class Vip extends Ingresso {
        private double adicional = 1.25;

        public double valorFinal() {
            return getValor() * getAdicional();
        }

        public double getAdicional() {
            return this.adicional;
        }

        public void setAdicional(double adicional) {
            this.adicional = adicional;
        }
    }

    abstract class Ingresso {
        private double valor = 100.50;
        private int categoria;

        public abstract double valorFinal();

        public double getValor() {
            return this.valor;
        }

        public void setValor(double valor) {
            this.valor = valor;
        }

        public int getCategoria() {
            return this.categoria;
        }

        public void setCategoria() {
            this.categoria = categoria;
        }
    }
}