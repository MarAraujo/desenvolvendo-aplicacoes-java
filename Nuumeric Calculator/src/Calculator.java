import entientes.Opperation;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

//=================================================================== Codigo Main =========================================================================

public class Calculator {
    Scanner sc = new Scanner(System.in);

    Opperation calc = new Opperation();

    public void menuBegin() {
        System.out.println("************************************************");
        System.out.println("************* Calculadora Digital **************");
        System.out.println("************************************************");

        System.out.println("Escolha a operação:\n1-Soma\n2-Subtração\n3-Multiplicação\n4-Divisão\n0-Sair");
    }

    public void digitalCalculator() {
        menuBegin();
        try {
            int chooseMenu = Integer.parseInt(sc.nextLine());

            if(chooseMenu >= 0 && chooseMenu <= 4){
                switch (chooseMenu) {
                    case 1:
                        System.out.println("************* Operação Soma *************");
                        inputDatas();
                        calc.sum();
                        System.out.println(calc.toString());
                        sc.nextLine();
                        sc.nextLine();
                        break;

                    case 2:
                        System.out.println("************* Operação Subtração *************");
                        inputDatas();
                        calc.subtration();
                        System.out.println(calc.toString());
                        sc.nextLine();
                        sc.nextLine();
                        break;
                    case 3:
                        System.out.println("************* Operação Multiplicação *************");
                        inputDatas();
                        calc.multiplication();
                        System.out.println(calc.toString());
                        sc.nextLine();
                        sc.nextLine();
                        break;
                    case 4:
                        System.out.println("************* Operação Divisão *************");
                        inputDatas();
                        calc.division();
                        System.out.println(calc.toString());
                        sc.nextLine();
                        sc.nextLine();
                        break;
                    case 0:
                        System.out.println("************* Calculadora Finalizada *************");
                        sc.close();
                        System.exit(0);
                        break;
                }

            }
            else {
                System.out.println("Valor Invalido!!\nPor favor digite uma opção dentro do menu!!\n" +
                        "Precione enter para continuar");
                sc.nextLine();
                digitalCalculator();
            }


        } catch (NumberFormatException ex) {
            System.out.println("Operação Invalida!!" +
                    "\nPressione enter para continuar......\n");
            sc.nextLine();
            sc.nextLine();
            digitalCalculator();
        } catch (InputMismatchException ex) {
            System.out.println("Operação Invalida!!" +
                    "\nPressione 'ENTER' para continuar......\n");
            sc.nextLine();
            sc.nextLine();
            digitalCalculator();
        } catch (IllegalArgumentException ex) {
            System.out.println("Não é possível Dividir números por zero!!!" +
                    "\nPressione 'ENTER' para continuar..." +
                    "\nRefaça a operação escolhida...\n");
            sc.nextLine();
            sc.nextLine();
            digitalCalculator();
        }
    }

    public void inputDatas() {
        System.out.print("Digite o Primeiro numero: ");
        calc.setNumber1(sc.nextDouble());
        System.out.print("Digite o Segundo numero: ");
        calc.setNumber2(sc.nextDouble());
    }

    public static void main(String[] args) {
        Locale.setDefault(new Locale("pt", "BR"));
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("#.##", symbols);
        Calculator operacao = new Calculator();
        while (true) {
            operacao.digitalCalculator();
        }
    }
}