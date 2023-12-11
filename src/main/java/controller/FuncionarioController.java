/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.controller;

import main.java.model.Endereco;
import main.java.model.Funcionario;
import main.java.view.FuncionarioForm;
import main.java.view.FuncionarioLogin;
import main.java.view.Home;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import main.java.view.View;

import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author lalve
 */
public class FuncionarioController {
    private Funcionario model;
    private FuncionarioForm view;

    public FuncionarioController(Funcionario model, FuncionarioForm view) {
        this.model = model;
        this.view = view;
    }

    public void cadastrarFuncionario(Funcionario funcionario) {
        model.cadastrarFuncionario(funcionario);
    }

    public void listarFuncionarios() {
        model.listarFuncionarios();
    }

    public boolean loginFuncionario(String userName, String email) {
        return model.loginFuncionario(userName, email);
    }

    public void atualizarFuncionario(Funcionario funcionario) {
        model.atualizarFuncionario(funcionario);
    }

    // public void exibirFuncionario(int id) {
    // Funcionario funcionario = model.obterFuncionario(id);
    // }

    public String validarFormulario(TextField cpfField, TextField nomeField, DatePicker dataNascPicker,
            TextField usernameField, TextField emailField, TextField localField, TextField cepField,
            TextField estadoField, TextField bairroField) {

        // Validar CPF
        if (cpfField.getText() == null || cpfField.getText().trim().isEmpty()) {
            return ("CPF é obrigatório.\n");
        }

        // Validar nome
        if (nomeField.getText() == null || nomeField.getText().trim().isEmpty()) {
            return ("Nome é obrigatório.\n");
        }

        // Validar data de nascimento
        if (dataNascPicker.getValue() == null) {
            return ("Data de nascimento é obrigatória.\n");
        }

        // Validar username
        if (usernameField.getText() == null || usernameField.getText().trim().isEmpty()) {
            return ("Username é obrigatório.\n");
        }

        // Validar email
        if (emailField.getText() == null || emailField.getText().trim().isEmpty()) {
            return ("Email é obrigatório.\n");
        }

        // Validar local
        if (localField.getText() == null || localField.getText().trim().isEmpty()) {
            return ("Local é obrigatório.\n");
        }

        // Validar CEP
        if (cepField.getText() == null || cepField.getText().trim().isEmpty()) {
            return ("CEP é obrigatório.\n");
        }

        // Validar estado
        if (estadoField.getText() == null || estadoField.getText().trim().isEmpty()) {
            return ("Estado é obrigatório.\n");
        }

        // Validar bairro
        if (bairroField.getText() == null || bairroField.getText().trim().isEmpty()) {
            return ("Bairro é obrigatório.\n");
        }

        return "";

    }

    public Funcionario montarObjetoFuncionario(TextField cpfField, TextField nomeField, DatePicker dataNascPicker,
            TextField usernameField, TextField emailField, TextField localField, TextField cepField,
            TextField estadoField, TextField bairroField) {

        // Criar um objeto do tipo Endereco
        Endereco endereco = new Endereco(localField.getText(), cepField.getText(), estadoField.getText(),
                bairroField.getText());

        // Criar um objeto do tipo Funcionario
        Funcionario funcionario = new Funcionario(true, endereco, cpfField.getText(), nomeField.getText(),
                java.sql.Date.valueOf(dataNascPicker.getValue()), usernameField.getText(), emailField.getText());

        return funcionario;
    }

    public void abrirTelaLogin(Scene scene) {
        FuncionarioLogin funcionarioLogin = new FuncionarioLogin();
        funcionarioLogin.setController(this);
        abrirTela(scene, funcionarioLogin);
    }

    public void abrirTelaFormulario(Scene scene) {
        FuncionarioForm funcionarioForm = new FuncionarioForm();
        funcionarioForm.setController(this);
        abrirTela(scene, funcionarioForm);
    }

    public void abrirHome(Scene scene) {
        Home home = new Home();
        abrirTela(scene, home);
    }

    public void abrirTela(Scene sceneAtual, View novaTela) {
        fecharView(sceneAtual);
        Stage stage = new Stage();
        stage.setScene(new Scene(novaTela.show()));
        stage.show();
    }

    public void fecharView(Scene scene) {
        Stage stage = (Stage) scene.getWindow();
        stage.close();
    }
}
