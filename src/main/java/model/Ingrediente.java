package main.java.model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class Ingrediente {

    private String nome;
    private int quantidade;

    public Ingrediente(String nome, int quantidade) {
        this.nome = nome;
        this.quantidade = quantidade;
    }

    

    public String getNome() {
        return nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void reabastecer() {
        quantidade += 10; // Adiciona 10 unidades ao estoque ao reabastecer
    }

}