package customerTable.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class dataModel{
    public String user = "paulo";
    public String password = "123456";
    public String connectionString = "jdbc:sqlserver://DESKTOP-91G10Q2\\SQLEXPRESS:5171;user=" 
        + user + ";password=" + password + ";";
    static Connection connection = null;
    static Statement statement = null;

    public Connection connect() throws SQLException{
        DriverManager.setLoginTimeout(23);
        connection = DriverManager.getConnection(connectionString);
        return connection;
        /*} catch (SQLException ex) {
            System.out.println("The following exception has occured: " + ex.getMessage());
        } finally {

            try {
                if (connection != null) {
                    connection.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException ex) {
                System.out.println("The following exception has occured: " + ex.getMessage());
            }*/
    }

    public void createDatabase(Connection conn) throws SQLException{
        String sql_stmt = "if ((SELECT count(name) FROM sys.databases WHERE name LIKE '%customers%') = 0 )\n" +
                "Begin\n" +
                "\tcreate database customers\n" +
                "end\n";
        statement = conn.createStatement();

        statement.executeUpdate(sql_stmt);

        System.out.println("customers_db has successfully been created");
    }

    public void createTable(Connection conn) throws SQLException{
        String sql_stmt = "use customers\n if (NOT EXISTS(SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME LIKE '%tb_customer_account%') )\n" +
                "Begin\n" +
                    "\tcreate table tb_customer_account(\n" +
                    "id_customer int IDENTITY(1,1) PRIMARY KEY," +
                    "cpf_cnpj varchar(255) NOT NULL," +
                    "nm_customer varchar(255) NOT NULL," +
                    "isActive int NOT NULL," +
                    "vl_total int NOT NULL)\n" +
                "end\n";

        
        statement = conn.createStatement();

        statement.executeUpdate(sql_stmt);

        System.out.println("tb_customer_account has successfully been created");
    }

    public void populateTable(int numero, Connection conn) throws SQLException{
        String[] numeros = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        String[] primeirosNomes = {"Paulo ", "Elisa ", "Adiginton ", "Edipo ", "Will ", "Seiji ", "José ", "Giovani ", "Jailson ", "Paulo2 ", "Elisa2 ", "Adiginton2 ", "Edipo2 ", "Will2 ", "Seiji2 ", "José2 ", "Giovani2 ", "Jailson2 "};
        String[] segundosNomes = {"Daniel ", "Martins ", "Jackson ", "Fernandes ", "Kennedy ", "Nakamjura ", "Daniel2 ", "Martins2 ", "Jackson2 ", "Fernandes2 ", "Kennedy2 ", "Nakamjura2 "};
        String[] sobreNomes = {"Carneiro", "de Souza", "Ferreira", "Gonçalves", "Fonseca", "Silva", "Carneiro2", "de Souza2", "Ferreira2", "Gonçalves2", "Fonseca2", "Silva2" };
        String cpf;
        String nome;
        int ativo;
        int valor;

        Random rand = new Random();

        for (int i = 0; i < numero; i++){
            //cpf
            String cpfCompleto = "";
            for (int cont = 0; cont < 11; cont++){
                cpfCompleto += numeros[rand.nextInt(numeros.length)];
            }
            cpf = cpfCompleto;

            //Nome
            String nomeCompleto = "";
            nomeCompleto += (primeirosNomes[rand.nextInt(primeirosNomes.length)]);
            nomeCompleto += (segundosNomes[rand.nextInt(segundosNomes.length)]);
            nomeCompleto += (sobreNomes[rand.nextInt(sobreNomes.length)]);
            nome = nomeCompleto;

            //Ativo
            ativo = (rand.nextInt(2) == 1) ? 1 : 0;

            //valor
            valor = rand.nextInt(40000);

            //popular Lista
            customerModel customer = new customerModel();
            customer.setCpfCnpj(cpf);
            customer.setIsActive(ativo);
            customer.setNmCustomer(nome);
            customer.setVlTotal(valor);

            String sql_stmt = "INSERT INTO tb_customer_account Values('" + customer.getCpfCnpj() +
                "', '" + customer.getNmCustomer() + 
                "', " + customer.getIsActive() + 
                ", " + customer.getVlTotal() +
                ")";
            
            statement = conn.createStatement();

            statement.executeUpdate(sql_stmt);
        }
    }

    public Boolean populateTable(List<customerModel> lista, int numero, Connection conn) throws SQLException{
        if (lista.isEmpty()) return false;
        while(!lista.isEmpty()){
            customerModel cliente = lista.remove(0);
            String sql_stmt = "INSERT INTO tb_customer_account Values('" + cliente.getCpfCnpj() +
                "', '" + cliente.getNmCustomer() + 
                "', " + cliente.getIsActive() + 
                ", " + cliente.getVlTotal() +
                ")";

            statement = conn.createStatement();

            statement.executeUpdate(sql_stmt);
        }
        return true;
    }

    public List<customerModel> getData(Connection conn) throws SQLException{
        String sql_stmt = "SELECT nm_customer, vl_total FROM tb_customer_account WHERE " +
            "vl_total > 560 AND id_customer BETWEEN 1500 AND 2700 ORDER BY vl_total DESC";
        List<customerModel> listaCliente = new ArrayList<customerModel>();

        statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(sql_stmt);

        if (!rs.next()) {
            listaCliente = null;
        } else {
            do {
                customerModel cliente = new customerModel();
                cliente.setNmCustomer(rs.getString("nm_customer"));
                cliente.setVlTotal(rs.getInt("vl_total"));
                listaCliente.add(cliente);
            } while (rs.next());
        }
        return listaCliente;
    }
}