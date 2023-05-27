import java.util.Scanner;


/**
 * Classe utilizada para demonstrar o uso da palavra chave throw,
 * utilizada quando queremos lançar uma exceção.
 */
import java.util.Scanner;

/**
 * Classe que demonstra o uso do bloco finally.
 */
public class ExemploFinally {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        while (true) {
            try {
                int dividendo, divisor;
                System.out.print("Digite o valor do dividendo: ");
                dividendo = s.nextInt();
                System.out.print("Digite o valor do divisor: ");
                divisor = s.nextInt();

                if (divisor == 0) {
                    throw new Exception("Nao eh permitido fazer uma divisao por zero!");
                }

                System.out.println(dividendo + " / " + divisor + " = " + (dividendo / divisor));
            } catch (Exception ex) {
                System.out.println("Erro: " + ex.getMessage());
            } finally {
                System.out.println("Bloco Finally.");
            }
        }
    }
}