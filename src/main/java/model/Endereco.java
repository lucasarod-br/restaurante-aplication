package main.java.model;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

public class Endereco {
    private String nome;
    private String local;
    private String cep;
    private String estado;
    private String bairro;
    private List<Endereco> enderecos;

    // Construtor padrão
    public Endereco() {
    }

    // Construtor padrão que recebe todos os dados
    public Endereco(String local, String cep, String estado, String bairro) {
        this.nome = nome;
        this.local = local;
        this.cep = cep;
        this.estado = estado;
        this.bairro = bairro;
    }

    // toString
    @Override
    public String toString() {
        return this.local + "," + this.cep + "," + this.estado + "," + this.bairro;
    }

    // Setters para todos os atributos
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    // Getters para todos os atributos
    public String getNome() {
        return nome;
    }

    public String getLocal() {
        return local;
    }

    public String getCep() {
        return cep;
    }

    public String getEstado() {
        return estado;
    }

    public String getBairro() {
        return bairro;
    }

    // Métodos para CRUD e manipulação de arquivos
    public void cadastrarEndereco(Endereco endereco) {

        try {
            FileWriter fileWriter = new FileWriter("funcionarios.txt", true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println(endereco.toString());
            printWriter.close();

        } catch (Exception e) {
            System.out.println("Erro ao escrever no arquivo");
            e.printStackTrace();
        }
    }

    public Endereco obterEndereco(int id) {
        // Implementação
        return null;
    }

    public List<Endereco> listarEnderecos() {
        // Implementação
        return null;
    }

}
