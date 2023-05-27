import entientes.CamaroteInferior;
import entientes.CamaroteSuperior;
import entientes.Ingresso;
import entientes.Normal;
import java.util.Scanner;

public class SistemaInterno {

    Scanner sc = new Scanner(System.in);

    public void PedirIngresso() {
        int categoria = 0;
        System.out.println("Escolha um tipo de :\n 1- Normal\n 2- Vip Super\n 3- Vip Padrão\n 0- Sair\n");

        try {
            categoria = Integer.parseInt(sc.nextLine());
            if (categoria > -1 && categoria < 5) {
                categoria = VerificacaoSegurança(categoria);
                DefinirCategoria(categoria);
            } else {
                System.out.println("Opcao Invalida Tente novamente, pressione Enter para continuar!!");
                sc.nextLine();
                PedirIngresso();
            }
        } catch (NumberFormatException ex) {
            System.out.println("Opcao Invalida Tente novamente, pressione Enter para continuar!!");
            sc.nextLine();
            PedirIngresso();
        }

    }

    public int VerificacaoSegurança(int categoria) {
        boolean condicao = categoria != 1 && categoria != 2 && categoria != 3 && categoria != 0;
        if (condicao) {
            while (condicao) {
                System.out.println("Digite apenas 1,2, 3 ou 0 por favor!");
                categoria = sc.nextInt();
            }
        }
        return categoria;
    }

    public void DefinirCategoria(int categoria) {
        switch (categoria) {
            case 1:
                Ingresso normal = new Normal();
                Valor(normal);
                break;
            case 2:
                System.out.print("Digite o valor Adicional do Camarote Superior: ");
                Ingresso superior = new CamaroteSuperior(sc.nextDouble());
                Valor(superior);
                break;
            case 3:
                System.out.print("Digite o valor Adicional do Camarote Inferior: ");
                Ingresso inferior = new CamaroteInferior(sc.nextDouble());
                Valor(inferior);
                break;
            case 0:
                System.exit(0);
                break;
        }
    }

    public void Valor(Ingresso aux) {
        double valor = aux.valorFinal();
        System.out.println("O valor final do seu ingresso é: " + valor);
    }

    public static void main(String[] args) {
        while (true){
            Scanner sc = new Scanner(System.in);
            SistemaInterno aux = new SistemaInterno();
            aux.PedirIngresso();
            System.out.println("Precione Enter para Continuar.....");
            sc.nextLine();
        }
    }
}