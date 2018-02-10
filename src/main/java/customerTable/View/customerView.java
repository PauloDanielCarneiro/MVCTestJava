package customerTable.View;

import customerTable.Model.customerModel;
import java.util.Scanner;

public class customerView{
    public static int telaInicial(){
        Scanner scanner = new Scanner(System.in);
        System.out.println( "Bem vindo ao teste de MVC em console." );
        System.out.println( "PAra realizar o teste, escolha:" );
        System.out.println( "0- Sair" );
        System.out.println( "1- Inserir dados manualmente" );
        System.out.println( "2- Inserir dados aleatorios automaticamente" );
        String escolha = scanner.next();
        int esc  = Integer.valueOf(escolha);
        while (esc < 0 && esc > 2){
            System.out.println( "Desculpe, opção invalida." );
            System.out.println( "Para realizar o teste, escolha uma opção:" );
            System.out.println( "0- Sair" );
            System.out.println( "1- Inserir dados manualmente" );
            System.out.println( "2- Inserir dados aleatórios automaticamente" );
            escolha = scanner.next();
            esc  = Integer.valueOf(escolha);
        }   
        scanner.close();
        return esc;
    }

    public static customerModel populateObject(customerModel customer){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o nome do cliente:");
        customer.setNmCustomer(scanner.next());
        System.out.println("Digite o cpf ou cnpj do cliente:");
        customer.setCpfCnpj(scanner.next());
        System.out.println("Digite 1 para cliente ativo, 2 para inativo:");
        customer.setIsActive(Integer.valueOf(scanner.next()));
        while(customer.getIsActive() != 0 && customer.getIsActive() != 1){
            System.out.println("Valor Invalido!");
            System.out.println("Digite 1 para cliente ativo, 2 para inativo:");
            customer.setIsActive(Integer.valueOf(scanner.next()));
        }
        System.out.println("Digite o valor total comprado pelo cliente:");
        customer.setVlTotal(Integer.valueOf(scanner.next()));
        scanner.close();

        return customer;
    }

	public static int quantidade() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite a quantidade de cliente que deseja inserir:");
        return Integer.valueOf(scanner.next());
	}

	public static void finalizar() {
        System.out.println("O programa foi finalizado");
	}

	public void exibeMedia(double media) {
        System.out.println("media: " + media);
	}

	public void exibeNome(String nmCustomer) {
        System.out.println(nmCustomer);
	}
}