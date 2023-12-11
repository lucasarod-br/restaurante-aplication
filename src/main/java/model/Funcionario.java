/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import main.java.packages.Pessoa;

/**
 *
 * @author lalve
 */

public class Funcionario implements Pessoa {
    private String id;
    private int codigo;
    private boolean status;
    private Endereco endereco; // Adicionando um atributo do tipo Endereco
    private String cpf;
    private String nome;
    private Date dataNascimento;
    private String userName;
    private String email;

    public Funcionario() {
        // colocar o id como uma string aleatória de 6 caracteres
        this.id = String.valueOf(Math.random()).substring(2, 8);
    }

    // Construtor da classe Funcionario
    public Funcionario(boolean status, Endereco endereco, String cpf, String nome, Date dataNascimento, String userName,
            String email) {
        this.id = String.valueOf(Math.random()).substring(2, 8);
        ;
        this.status = status;
        this.endereco = endereco;
        this.cpf = cpf;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.userName = userName;
        this.email = email;
    }

    // Métodos para CRUD e manipulação de arquivos

    public void cadastrarFuncionario(Funcionario funcionario) {
        try {
            FileWriter fileWriter = new FileWriter("funcionarios.txt", true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println(funcionario.toString());
            printWriter.close();
        } catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo");
            e.printStackTrace();
        }
    }

    public boolean loginFuncionario(String userName, String email) {
        System.out.println("Entrou no model");
        BufferedReader reader;
        try {
            System.out.println("Entrou no try");
            reader = new BufferedReader(new FileReader("funcionarios.txt"));
            System.out.println("Entrou no reader");
            String line = reader.readLine();
            System.out.println("Entrou no line");
            while (line != null) {
                System.out.println("Entrou no while");
                String[] fields = line.split(",");
                System.out.println(fields[0]);
                String userNameFile = fields[4];
                String emailFile = fields[5];
                if (userNameFile.equals(userName) && emailFile.equals(email)) {
                    return true;
                }
                line = reader.readLine();
                
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo");
            e.printStackTrace();
        }
        return false;
    }

    // public void cadastrarFuncionario(Funcionario funcionario) {
    // Gson gson = new Gson();
    // String json = gson.toJson(funcionario);

    // try (FileWriter fileWriter = new FileWriter("funcionarios.json", true)) {
    // fileWriter.write(json);
    // } catch (IOException e) {
    // System.out.println("Erro ao escrever no arquivo");
    // e.printStackTrace();
    // }
    // }

    // Implementação dos métodos da interface Pessoa

    @Override
    public String toString() {
        // formato para salvar num arquivo de texto
        return this.id + "," + this.cpf + "," + this.nome
                + "," + this.dataNascimento + "," + this.userName + "," + this.email;
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getCpf() {
        return cpf;
    }

    @Override
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public Date getDataNascimento() {
        return dataNascimento;
    }

    @Override
    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @Override
    public boolean isStatus() {
        return status;
    }

    @Override
    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    // Getters específicos para a classe Funcionario

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    // Getter e Setter específico para o atributo Endereco
    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Funcionario obterFuncionario(int id) {
        // Implementação
        return null;
    }

    public List<Funcionario> listarFuncionarios() {
        // Implementação
        return null;
    }

    public void atualizarFuncionario(Funcionario funcionario) {
        // Implementação
    }

    public void excluirFuncionario(int id) {
        // Implementação
    }
}
