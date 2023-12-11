/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package restauranteapplication;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.java.controller.FuncionarioController;
import main.java.model.Funcionario;
import main.java.view.FuncionarioForm;

/**
 *
 * @author lalve
 */

public class RestauranteApplication extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        launch(args);
    }

    public void start(Stage primaryStage) {
        // Criar instâncias do modelo, visão e controlador
        Funcionario model = new Funcionario();
        FuncionarioForm view = new FuncionarioForm();
        FuncionarioController controller = new FuncionarioController(model, view);

        // Configurar a visão com o controlador
        view.setController(controller);

        // Exibir a janela
        Scene scene = new Scene(view.show(), 800, 600);
        primaryStage.setTitle("Cadastro de Funcionário");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
