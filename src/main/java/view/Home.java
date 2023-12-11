package main.java.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.java.controller.FuncionarioController;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Home {

    private FuncionarioController controller;

    public GridPane show() {
        GridPane gridPane = new GridPane();

        Button mesasButton = new Button("Mesas");
        mesasButton.setPrefSize(200, 100);
        mesasButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 20px;");
        mesasButton.setOnAction(e -> {
            // Abra a tela de mesas aqui
            GerenciadorMesas gerenciadorMesas = new GerenciadorMesas();
            gerenciadorMesas.setController(controller);
            gridPane.getChildren().clear();
            gridPane.getChildren().add(gerenciadorMesas.show());
            
        });

        Button pedidosButton = new Button("Pedidos");
        pedidosButton.setPrefSize(200, 100);
        pedidosButton.setStyle("-fx-background-color: #008CBA; -fx-text-fill: white; -fx-font-size: 20px;");
        pedidosButton.setOnAction(e -> {
            // Abra a tela de pedidos aqui
        });

        Button estoqueButton = new Button("Estoque");
        estoqueButton.setPrefSize(200, 100);
        estoqueButton.setStyle("-fx-background-color: #f44336; -fx-text-fill: white; -fx-font-size: 20px;");
        estoqueButton.setOnAction(e -> {
            // Abra a tela de estoque aqui
        });

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(mesasButton, pedidosButton, estoqueButton);

        gridPane.getChildren().add(layout);

        return gridPane;
    }

    public void setController(FuncionarioController controller) {
        this.controller = controller;
    }
}