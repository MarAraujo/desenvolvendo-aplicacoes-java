
import entities.Square;
import entities.Cicle;
import entities.Triangle;
import entities.Rectangle;

import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(new Locale("pt", "BR"));
        Scanner sc = new Scanner(System.in);
        Square sqr = new Square();
        Cicle cl = new Cicle();
        Triangle tgl = new Triangle();
        Rectangle rtg = new Rectangle();
        boolean loop = true;
        double data1, data2;
        int choose = 0;

        while (loop) {
            try {
                System.out.println("Digite nas opções qual area de qual Figura geometrica deseja Calcular:");
                System.out.print("1-Area do Quadrado\n2-Area do Circulo\n" +
                        "3-Area do Triândulo Retandulo\n4-Area do Retangulo\n0-Sair\n");
                choose = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException ex) {
                System.out.println("Valor Invalido Digite uma opção correta!!");
                continue;
            }

            switch (choose) {
                case 1:
                    System.out.println("Calculando a Area do Quadrado!");
                    System.out.println("Informe o lado do quadrado: ");
                    data1 = sc.nextDouble();
                    sqr.setSide(data1);
                    System.out.println("A área do Quadrado: " + sqr.areaFigureSquare());
                    System.out.println("Precione spaço ou qualquer tecla para continuar!!");
                    sc.nextLine();
                    sc.nextLine();
                    break;
                case 2:
                    System.out.println("Calculando a Area do Circulo!");
                    System.out.println("Informe o raio do circulo: ");
                    data1 = sc.nextDouble();
                    cl.setRadius(data1);
                    System.out.println("A área do Circulo: " + cl.areFigureCicle());
                    System.out.println("Precione spaço ou qualquer tecla para continuar!!");
                    sc.nextLine();
                    sc.nextLine();
                    break;
                case 3:
                    System.out.println("Calculando a Area do Triâgulo Retangulo!");
                    System.out.println("Informe a base: ");
                    data1 = sc.nextDouble();
                    System.out.println("Informe a altura: ");
                    data2 = sc.nextDouble();
                    System.out.println("A área do Triângulo Retangulo: " + tgl.areaFigure(data1, data2));
                    System.out.println("Precione spaço ou qualquer tecla para continuar!!");
                    sc.nextLine();
                    sc.nextLine();
                    break;
                case 4:
                    System.out.println("Calculando a Area do Retangulo!");
                    System.out.println("Informe a base: ");
                    rtg.setBase(sc.nextDouble());
                    System.out.println("Informe a altura: ");
                    rtg.setHeight(sc.nextDouble());
                    System.out.println("A área do Retangulo: " + rtg.areaFigure());
                    System.out.println("Precione spaço ou qualquer tecla para continuar!!");
                    sc.nextLine();
                    sc.nextLine();
                    break;
                case 0:
                    System.out.println("\nFim da Calculadora!!");
                    loop = false;
                    break;

            }
        }

    }
}