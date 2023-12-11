package main.java.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import main.java.controller.FuncionarioController;

import java.util.Arrays;
import java.util.List;

public class GerenciadorMesas implements View {

    private FuncionarioController controller;

    public void setController(FuncionarioController controller) {
        this.controller = controller;
    }

    @Override
    public Parent show() {



        List<Mesa> mesas = Arrays.asList(
                new Mesa(1, "Livre"),
                new Mesa(2, "Ocupada"),
                new Mesa(3, "Livre"),
                new Mesa(4, "Ocupada"),
                new Mesa(5, "Livre")
        );

        // Layout principal
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(30);
        gridPane.setVgap(30);
        gridPane.setPadding(new Insets(20));

        int col = 0;
        int row = 0;
        for (Mesa mesa : mesas) {
            addToGrid(gridPane, mesa, col, row);
            col++;
            if (col == 3) {
                col = 0;
                row++;
            }
        }

        return gridPane;
    }

    private void addToGrid(GridPane gridPane, Mesa mesa, int col, int row) {
        String status = mesa.getStatus();
        Button acaoButton;
        if (status.equals("Livre")) {
            acaoButton = new Button("Iniciar Atendimento");
            acaoButton.setStyle(
                    "-fx-background-color: #4a90e2;"
                            + "-fx-text-fill: #ffffff;"
                            + "-fx-font-size: 12pt;"
            );

        } else {
            acaoButton = new Button("Encerrar Atendimento");
            acaoButton.setStyle(
                    "-fx-background-color: red;"
                            + "-fx-text-fill: #ffffff;"
                            + "-fx-font-size: 12pt;"
            );

        }
        acaoButton.setOnAction(e -> {
            // Adicionar a lógica para iniciar ou encerrar atendimento
            if (mesa.getStatus().equals("Livre")) {
                System.out.println("Iniciando atendimento na Mesa " + mesa.getNumero());
                mesa.iniciarAtendimento();
            } else {
                System.out.println("Encerrando atendimento na Mesa " + mesa.getNumero());
                mesa.encerrarAtendimento();
            }
            atualizarStatusMesa(gridPane, mesa, col, row);
        });

        VBox mesaBox = new VBox(10);
        mesaBox.setAlignment(Pos.CENTER);
        mesaBox.setStyle("-fx-border-color: #dddddd; -fx-border-width: 1px; -fx-padding: 16px;");

        mesaBox.getChildren().addAll(
                new Label("Mesa " + mesa.getNumero()),
                new Label("Status: " + mesa.getStatus()),
                acaoButton
        );

        gridPane.add(mesaBox, col, row);
    }

    private void atualizarStatusMesa(GridPane gridPane, Mesa mesa, int col, int row) {
        // Limpar a célula do GridPane
        gridPane.getChildren().removeIf(node -> GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row);
        // Adicionar a mesa atualizada
        addToGrid(gridPane, mesa, col, row);
    }

    private static class Mesa {

        private int numero;
        private String status;

        public Mesa(int numero, String status) {
            this.numero = numero;
            this.status = status;
        }

        public int getNumero() {
            return numero;
        }

        public String getStatus() {
            return status;
        }

        public void iniciarAtendimento() {
            status = "Ocupada";
        }

        public void encerrarAtendimento() {
            status = "Livre";
        }
    }
}