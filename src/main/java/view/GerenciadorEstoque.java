/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package main.java.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import main.java.controller.EstoqueController;
import main.java.controller.FuncionarioController;
import main.java.model.Estoque;
import main.java.model.Ingrediente;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 *
 * @author lalve
 */

public class GerenciadorEstoque implements View {

    private EstoqueController controller;
    private Stage currentStage;

    public GerenciadorEstoque() {
        this.controller = new EstoqueController(new Estoque(), this);
    }

    public GerenciadorEstoque(EstoqueController controller) {
        this.controller = controller;
    }

    public void setCurrentStage(Stage currentStage) {
        this.currentStage = currentStage;
    }

    @Override
    public Parent show() {
        Map<String, Integer> ingredientes = controller.lerIngredientes();

        // Layout principal
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(30);
        gridPane.setVgap(30);
        gridPane.setPadding(new Insets(20));

        int col = 0;
        int row = 0;

        List<Ingrediente> listaIngredientes = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : ingredientes.entrySet()) {
            String nome = entry.getKey();
            int quantidade = entry.getValue();
            listaIngredientes.add(new Ingrediente(nome, quantidade));

            addToGrid(gridPane, new Ingrediente(nome, quantidade), col, row);
            col++;
            if (col == 3) {
                col = 0;
                row++;
            }
        }

        // Adicionando o GridPane a um ScrollPane
        ScrollPane scrollPane = new ScrollPane(gridPane);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        // Seção de resumo do estoque
        HBox resumoEstoque = new HBox(20);
        resumoEstoque.setAlignment(Pos.CENTER);
        resumoEstoque.setPadding(new Insets(20));

        Button adicionarIngredienteButton = new Button("Adicionar Ingrediente");

        adicionarIngredienteButton.setStyle(
                "-fx-background-color: #4a90e2;"
                        + "-fx-text-fill: #ffffff;"
                        + "-fx-font-size: 14pt;"
                        + "-fx-font-weight: bold;");

        adicionarIngredienteButton.setOnAction(e -> {
            // Adicionar a lógica para adicionar um novo ingrediente ao estoque
            //pegar o stage atual e passar para o método mostrarTelaIngrediente
            this.mostrarTelaIngrediente(null, true);
        });

        resumoEstoque.getChildren().addAll(adicionarIngredienteButton);

        // VBox principal
        VBox contentBox = new VBox(20);
        contentBox.getChildren().addAll(scrollPane, resumoEstoque);

        if (currentStage != null) {
            currentStage.setScene(new Scene(contentBox));
        }

        return contentBox;
    }

    public void mostrarTelaIngrediente(Ingrediente ingrediente, boolean novoIngrediente) {
        Stage stage = new Stage();
        VBox vbox = new VBox();

        TextField nomeField = new TextField();
        if (novoIngrediente) {
            nomeField.setPromptText("Nome do ingrediente");
            vbox.getChildren().add(nomeField);
        } else {
            Label label = new Label("Quantidade a adicionar de " + ingrediente.getNome());
            vbox.getChildren().add(label);
        }

        TextField quantidadeField = new TextField();
        quantidadeField.setPromptText("Quantidade");
        vbox.getChildren().add(quantidadeField);

        Button addButton = new Button(novoIngrediente ? "Criar" : "Adicionar");
        addButton.setOnAction(e -> {
            String nome = nomeField.getText();
            int quantidade = Integer.parseInt(quantidadeField.getText());
            if (novoIngrediente) {
                controller.criarIngrediente(nome, quantidade);
            } else {
                controller.adicionarIngrediente(ingrediente, quantidade);
            }
            stage.close();
            this.show();
        });
        vbox.getChildren().add(addButton);

        Button cancelButton = new Button("Cancelar");
        cancelButton.setOnAction(e -> stage.close());
        vbox.getChildren().add(cancelButton);

        stage.setScene(new Scene(vbox));
        stage.show();
    }

    private void addToGrid(GridPane gridPane, Ingrediente ingrediente, int col, int row) {
        Button reabastecerButton = new Button("Reabastecer");

        reabastecerButton.setStyle(
                "-fx-background-color: #4a90e2;"
                        + "-fx-text-fill: #ffffff;"
                        + "-fx-font-size: 12pt;");

        reabastecerButton.setOnAction(e -> {
            this.mostrarTelaIngrediente(ingrediente, false);
            this.show();
        });

        VBox ingredienteBox = new VBox(10);
        ingredienteBox.setAlignment(Pos.CENTER);
        ingredienteBox.setStyle("-fx-border-color: #dddddd; -fx-border-width: 1px; -fx-padding: 16px;");

        // Adicionando uma imagem ao ingrediente
        Image ingredienteImage = new Image("https://via.placeholder.com/50");
        ImageView imageView = new ImageView(ingredienteImage);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);

        ingredienteBox.getChildren().addAll(
                imageView,
                new Label(ingrediente.getNome()),
                new Label("Quantidade: " + ingrediente.getQuantidade()),
                new HBox(10, reabastecerButton));

        gridPane.add(ingredienteBox, col, row);
    }

}
