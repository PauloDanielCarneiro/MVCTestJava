package customerTable.Controller;



import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import customerTable.Model.*;
import customerTable.View.*;

public class customerController{

	public void fluxo(Connection conn) {
        List<customerModel> clientes = new ArrayList<>();
        decideInsersao(conn);
        clientes = selecionarMedia(conn);
        double media = calculaMedia(clientes);
        exibicaoFinal(media, clientes);
        encerra();
	}

    public void IniciaPrograma(){
        Connection connection = null;
        dataModel data = new dataModel();
        try{
            connection = data.connect();
            data.createDatabase(connection);
            data.createTable(connection);
            fluxo(connection);
        }catch(SQLException ex){
            System.out.println("Erro ocorrido: " + ex.getMessage());
        }finally{
            try {
                if (connection != null) {
                    connection.close();
                }                
            } catch (SQLException ex) {
                System.out.println("The following exception has occured: " + ex.getMessage());
            }
        }
        
    }

    public void decideInsersao(Connection conn){
        int escolha = customerView.telaInicial();
        if (escolha == 0) encerra();
        int quantidade = customerView.quantidade();
        if(escolha == 1) insere(quantidade, conn);
        else insereAutomatico(quantidade, conn);
    }

	private void insereAutomatico(int quantidade,Connection conn) {
        try {
            dataModel data = new dataModel();
			data.populateTable(quantidade, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void insere(int quantidade, Connection conn) {
        List<customerModel> consumidores = new ArrayList<>();
        for (int contador = 0; contador < quantidade; contador++){
            customerModel customer = new customerModel();
            consumidores.add(customerView.populateObject(customer));
        }
        try { 
            dataModel data = new dataModel();
            data.populateTable(consumidores, quantidade, conn);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("Erro: " + e.getMessage());
        }

	}

	public void encerra() {
        customerView.finalizar();
        System.exit(0);
	}

    public List<customerModel> selecionarMedia(Connection conn){
        dataModel data = new dataModel();
        List<customerModel> clientes = new ArrayList<>();
        try {
			clientes = data.getData(conn);
		} catch (SQLException e) {
			e.printStackTrace();
        }
        return clientes;
    }

	public double calculaMedia(List<customerModel> clientes) {
        long total = 0;
        double quantidadeDeClientes = (double)clientes.size();
        for(customerModel cliente : clientes){
            total += (long) cliente.getVlTotal();
        }
        return total/quantidadeDeClientes;
	}

	private void exibicaoFinal(double media, List<customerModel> clientes) {
        customerView view = new customerView();
        view.exibeMedia(media);
        for (customerModel customer: clientes){
            view.exibeNome(customer.getNmCustomer());
        }
	}
    
}