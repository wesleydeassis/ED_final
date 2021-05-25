package cliente;

import java.time.LocalDate;

public class Clientes {
    
    private String CPF_RNE;
    private String Nome;
    private String Telefone;
    private String Endereco;
    private LocalDate DataCadastro;

    public Clientes(String CPF_RNE, String Nome, String Telefone, String Endereco, LocalDate DataCadastro) {
        this.CPF_RNE = CPF_RNE; 
        this.Nome = Nome;
        this.Telefone = Telefone;
        this.Endereco = Endereco;
        this.DataCadastro = DataCadastro;
    }

    public String getCPF_RNE() {
        return CPF_RNE;
    }

    public void setCPF_RNE(String cPF_RNE) {
        CPF_RNE = cPF_RNE;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getTelefone() {
        return Telefone;
    }

    public void setTelefone(String telefone) {
        Telefone = telefone;
    }

    public String getEndereco() {
        return Endereco;
    }

    public void setEndereco(String endereco) {
        Endereco = endereco;
    }

    public LocalDate getDataCadastro() {
        return DataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        DataCadastro = dataCadastro;
    }
}