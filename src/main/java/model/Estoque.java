package main.java.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Estoque {
    private HashMap<String, Integer> produtos;

    // Construtor padrão
    public Estoque() {
        this.produtos = new HashMap<>();
    }

    public void adicionarIngrediente(Ingrediente ingrediente, int quantidade) {
        // Encontrar o ingrediente no arquivo de texto e adicionar a quantidade ao estoque
        Map<String, Integer> ingredientes = lerIngredientes();
        int quantidadeAtual = ingredientes.getOrDefault(ingrediente.getNome(), 0);
        ingredientes.put(ingrediente.getNome(), quantidadeAtual + quantidade);
        salvarIngredientes(ingredientes);
    }

    public void salvarIngredientes(Map<String, Integer> ingredientes) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("ingredientes.txt"))) {
            for (Map.Entry<String, Integer> entry : ingredientes.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void criarIngrediente(String nome, int quantidade) {
        // Adicionar um novo ingrediente ao arquivo de texto
        Map<String, Integer> ingredientes = lerIngredientes();
        ingredientes.put(nome, quantidade);
        salvarIngredientes(ingredientes);
    }

    public Map<String, Integer> lerIngredientes() {
        Map<String, Integer> ingredientes = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("ingredientes.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(",");
                ingredientes.put(partes[0], Integer.parseInt(partes[1]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ingredientes;
    }

    // Método para incluir um item com quantidade zerada (padrão) no HashMap
    public void incluirItem(String nome) {
        produtos.put(nome, 0);
    }

    // Método para remover um item do HashMap
    public void removerItem(String nome) {
        produtos.remove(nome);
    }

    // Método para adicionar quantidade a um item existente no HashMap
    public void adicionarQuantidade(String nome, int quantidade) {
        int quantidadeAtual = produtos.getOrDefault(nome, 0);
        produtos.put(nome, quantidadeAtual + quantidade);
    }

    // Método para remover quantidade de um item existente no HashMap
    public void removerQuantidade(String nome, int quantidade) {
        int quantidadeAtual = produtos.getOrDefault(nome, 0);
        int novaQuantidade = Math.max(quantidadeAtual - quantidade, 0);
        produtos.put(nome, novaQuantidade);
    }

    // Método para obter a quantidade de um item no estoque
    public int obterQuantidade(String nome) {
        return produtos.getOrDefault(nome, 0);
    }

    // Método para imprimir o estado atual do estoque
    public void imprimirEstoque() {
        System.out.println("=== Estoque Atual ===");
        for (String nome : produtos.keySet()) {
            int quantidade = produtos.get(nome);
            System.out.println(nome + ": " + quantidade);
        }
        System.out.println("=====================");
    }
}
