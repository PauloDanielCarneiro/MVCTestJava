package customerTable.Model;

public class customerModel{

    private int id_customer;

    private String cpf_cnpj;

    private String nm_customer;

    private int isActive;

    private int vl_total;

    //Contrutores

    public customerModel(){}

    public customerModel(String codigo, String nome, int ativo, int vl_total){
        this.cpf_cnpj = codigo;
        this.nm_customer = nome;
        this.isActive = ativo;
        this.vl_total = vl_total;

    }
    // Metodos GET
    public int getIdCustomer(){
        return this.id_customer;
    }

    public String getCpfCnpj(){
        return this.cpf_cnpj;
    }

    public String getNmCustomer(){
        return this.nm_customer;
    }

    public int getIsActive(){
        return this.isActive;
    }

    public int getVlTotal(){
        return this.vl_total;
    }

    //Metodos SET

    public void setCpfCnpj(String cpf){
        this.cpf_cnpj = cpf;
    }

    public void setNmCustomer(String nome){
        this.nm_customer = nome;
    }

    public void setIsActive(int ativo){
        this.isActive = ativo;
    }

    public void setVlTotal(int valor){
        this.vl_total = valor;
    }
}