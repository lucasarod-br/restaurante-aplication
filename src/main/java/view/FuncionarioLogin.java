package main.java.view;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.java.controller.FuncionarioController;

public class FuncionarioLogin {

    private FuncionarioController controller;

    public static void main(String[] args) {
        // TODO code application logic here
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    public void setController(FuncionarioController controller) {
        this.controller = controller;
    }

    public GridPane show() {

        // Criar um painel em grade para organizar os componentes
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        // Criar um texto de título e adicioná-lo ao painel
        Text scenetitle = new Text("Login de Funcionário");
        scenetitle.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        grid.add(scenetitle, 0, 0, 2, 1);

        // Criar uma seção de dados pessoais e adicioná-la ao painel
        VBox dadosPessoais = new VBox();
        dadosPessoais.setSpacing(10);
        dadosPessoais.setPadding(new Insets(10, 10, 10, 10));
        dadosPessoais.setStyle(
                "-fx-border-color: #4a90e2; -fx-border-width: 2; -fx-border-radius: 5; -fx-background-color: #f9f9f9;");

        Label dadosPessoaisLabel = new Label("Dados Pessoais");
        dadosPessoaisLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        dadosPessoaisLabel.setTextFill(Color.web("#4a90e2"));

        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();

        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();

        dadosPessoais.getChildren().addAll(dadosPessoaisLabel, usernameLabel, usernameField, emailLabel, emailField);

        Button backButton = new Button("Voltar");
        backButton.setOnAction(e -> {
            controller.abrirTelaFormulario(backButton.getScene());
        });
        backButton.setStyle("-fx-background-color: #0f0f0f; -fx-text-fill: #ffffff;");

        Label feedbackLabel = new Label("");
        feedbackLabel.setTextFill(Color.web("#ff0000"));
        dadosPessoais.getChildren().add(feedbackLabel);

        Button loginButton = new Button("Login");
        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String email = emailField.getText();

            // Verificar se os campos de username e email estão vazios
            if (username.isEmpty() || email.isEmpty()) {
                // Mostrar uma mensagem de erro
                feedbackLabel.setText("Por favor, preencha todos os campos.");
            } else {
                // Tentar fazer o login
                System.out.println("Tentando fazer o login");
                boolean login = controller.loginFuncionario(username, email);
                System.out.println(login);
                if (login) {
                    // Abrir a tela de home
                    controller.abrirHome(loginButton.getScene());
                } else {
                    // Mostrar uma mensagem de erro
                    feedbackLabel.setText("Username ou email incorretos.");
                }
            }
        });

        loginButton.setStyle("-fx-background-color: #4a90e2; -fx-text-fill: #ffffff;");
        dadosPessoais.getChildren().addAll(backButton, loginButton);

        grid.add(dadosPessoais, 0, 1);

        return grid;

    }

}
