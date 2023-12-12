package main.java.controller;

import java.util.Map;

import main.java.model.Estoque;
import main.java.model.Ingrediente;
import main.java.view.GerenciadorEstoque;

public class EstoqueController {
    private Estoque model;
    private GerenciadorEstoque view;

    public EstoqueController(Estoque model, GerenciadorEstoque view) {
        this.model = model;
        this.view = view;
    }

    public boolean adicionarIngrediente(Ingrediente ingrediente, int quantidade) {
        return model.adicionarIngrediente(ingrediente, quantidade);
    }

    public boolean criarIngrediente(String nome, int quantidade) {
        return model.criarIngrediente(nome, quantidade);
    }

    // public void reabastecerEstoque() {
    //     model.reabastecerEstoque();
    // }

    public Map<String, Integer> lerIngredientes() {
        return model.lerIngredientes();

    }

    // public void atualizarEstoque() {
    //     model.atualizarEstoque();
    // }
    
}
