package thiagocury.eti.br.ccf;

import java.io.Serializable;

/**
 * Created by aluno on 24/12/2015.
 */
public class Cliente implements Serializable {

    private long idcliente;
    private String nome;
    private String rg;
    private String cpf;
    private String endereco;
    private String numeroendereco;
    private String bairro;
    private String cidade;
    private String uf;
    private String cep;
    private String telefonefixo;
    private String telefonecelular;
    private String email;
    private String obs;

    public Cliente() {
    }

    public Cliente(long idcliente, String nome, String rg, String cpf, String endereco, String numeroendereco, String bairro, String cidade, String uf, String cep, String telefonefixo, String telefonecelular, String email, String obs) {
        this.idcliente = idcliente;
        this.nome = nome;
        this.rg = rg;
        this.cpf = cpf;
        this.endereco = endereco;
        this.numeroendereco = numeroendereco;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.cep = cep;
        this.telefonefixo = telefonefixo;
        this.telefonecelular = telefonecelular;
        this.email = email;
        this.obs = obs;
    }

    public long getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(long idcliente) {
        this.idcliente = idcliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNumeroendereco() {
        return numeroendereco;
    }

    public void setNumeroendereco(String numeroendereco) {
        this.numeroendereco = numeroendereco;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getTelefonefixo() {
        return telefonefixo;
    }

    public void setTelefonefixo(String telefonefixo) {
        this.telefonefixo = telefonefixo;
    }

    public String getTelefonecelular() {
        return telefonecelular;
    }

    public void setTelefonecelular(String telefonecelular) {
        this.telefonecelular = telefonecelular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "idcliente=" + idcliente +
                ", nome='" + nome + '\'' +
                ", rg='" + rg + '\'' +
                ", cpf='" + cpf + '\'' +
                ", endereco='" + endereco + '\'' +
                ", numeroendereco='" + numeroendereco + '\'' +
                ", bairro='" + bairro + '\'' +
                ", cidade='" + cidade + '\'' +
                ", uf='" + uf + '\'' +
                ", cep='" + cep + '\'' +
                ", telefonefixo='" + telefonefixo + '\'' +
                ", telefonecelular='" + telefonecelular + '\'' +
                ", email='" + email + '\'' +
                ", obs='" + obs + '\'' +
                '}';
    }
}//fecha classe
