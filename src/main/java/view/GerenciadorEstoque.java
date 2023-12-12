package main.java.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.java.controller.EstoqueController;
import main.java.model.Estoque;
import main.java.model.Ingrediente;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GerenciadorEstoque implements View {

    private EstoqueController controlador;
    private Parent interfaceAtual;

    public GerenciadorEstoque() {
        this.controlador = new EstoqueController(new Estoque(), this);
    }

    public GerenciadorEstoque(EstoqueController controlador) {
        this.controlador = controlador;
    }

    @Override
    public Parent show() {
        Map<String, Integer> ingredientes = controlador.lerIngredientes();

        // Layout principal
        GridPane grade = criarLayoutPrincipal();

        // Adicionando um título à página
        Text titulo = criarTitulo();

        List<Ingrediente> listaIngredientes = popularGridPane(grade, ingredientes);

        // Adicionando o GridPane a um ScrollPane
        ScrollPane rolagem = criarScrollPane(grade);

        HBox caixasDeSelecao = criarCaixasDeSelecao(listaIngredientes, grade);

        HBox caixaDeBusca = criarCaixaDeBusca(listaIngredientes, grade, caixasDeSelecao);

        // Seção de resumo do estoque
        HBox resumoEstoque = criarResumoEstoque();

        // VBox principal
        VBox caixaDeConteudo = new VBox(20, titulo, caixaDeBusca, rolagem, resumoEstoque);
        this.interfaceAtual = caixaDeConteudo;

        return caixaDeConteudo;
    }

    private GridPane criarLayoutPrincipal() {
        GridPane grade = new GridPane();
        grade.setAlignment(Pos.CENTER);
        grade.setHgap(30);
        grade.setVgap(30);
        grade.setPadding(new Insets(20));
        return grade;
    }

    private Text criarTitulo() {
        Text titulo = new Text("Gerenciador de Estoque");
        titulo.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        return titulo;
    }

    private List<Ingrediente> popularGridPane(GridPane grade, Map<String, Integer> ingredientes) {
        int col = 0;
        int row = 0;

        List<Ingrediente> listaIngredientes = new ArrayList<>();

        for (Map.Entry<String, Integer> entrada : ingredientes.entrySet()) {
            String nome = entrada.getKey();
            int quantidade = entrada.getValue();
            Ingrediente ingrediente = new Ingrediente(nome, quantidade);
            listaIngredientes.add(ingrediente);

            adicionarNaGrade(grade, ingrediente, col, row);
            col++;
            if (col == 3) {
                col = 0;
                row++;
            }
        }
        return listaIngredientes;
    }

    private ScrollPane criarScrollPane(GridPane grade) {
        ScrollPane rolagem = new ScrollPane(grade);
        rolagem.setFitToWidth(true);
        rolagem.setFitToHeight(true);
        return rolagem;
    }

    private HBox criarCaixasDeSelecao(List<Ingrediente> listaIngredientes, GridPane grade) {
        CheckBox caixaSelecaoAcabando = new CheckBox("Ingredientes acabando");
        CheckBox caixaSelecaoAcabados = new CheckBox("Ingredientes acabados");

        caixaSelecaoAcabando.setStyle("-fx-font-size: 14pt; -fx-background-color: #f2f2f2; -fx-padding: 10px;");
        caixaSelecaoAcabados.setStyle("-fx-font-size: 14pt; -fx-background-color: #f2f2f2; -fx-padding: 10px;");

        caixaSelecaoAcabando.setOnAction(
                e -> lidarComCaixaSelecaoAcabando(caixaSelecaoAcabando, caixaSelecaoAcabados, listaIngredientes,
                        grade));
        caixaSelecaoAcabados.setOnAction(
                e -> lidarComCaixaSelecaoAcabados(caixaSelecaoAcabando, caixaSelecaoAcabados, listaIngredientes,
                        grade));

        HBox caixasDeSelecao = new HBox(10, caixaSelecaoAcabando, caixaSelecaoAcabados);
        caixasDeSelecao.setAlignment(Pos.CENTER);

        return caixasDeSelecao;
    }

    private void lidarComCaixaSelecaoAcabando(CheckBox caixaSelecaoAcabando, CheckBox caixaSelecaoAcabados,
            List<Ingrediente> listaIngredientes, GridPane grade) {
        if (caixaSelecaoAcabando.isSelected()) {
            caixaSelecaoAcabados.setSelected(false);
            List<Ingrediente> listaFiltrada = listaIngredientes.stream()
                    .filter(i -> i.getQuantidade() < 10)
                    .collect(Collectors.toList());
            atualizarGrade(grade, listaFiltrada);
        } else {
            atualizarGrade(grade, listaIngredientes);
        }
    }

    private void atualizarGrade(GridPane grade, List<Ingrediente> ingredientes) {
        grade.getChildren().clear();
        int col = 0;
        int row = 0;
        for (Ingrediente ingrediente : ingredientes) {
            adicionarNaGrade(grade, ingrediente, col, row);
            col++;
            if (col == 3) {
                col = 0;
                row++;
            }
        }
    }

    private void lidarComCaixaSelecaoAcabados(CheckBox caixaSelecaoAcabando, CheckBox caixaSelecaoAcabados,
            List<Ingrediente> listaIngredientes, GridPane grade) {
        if (caixaSelecaoAcabados.isSelected()) {
            caixaSelecaoAcabando.setSelected(false);
            List<Ingrediente> listaFiltrada = listaIngredientes.stream()
                    .filter(i -> i.getQuantidade() == 0)
                    .collect(Collectors.toList());
            atualizarGrade(grade, listaFiltrada);
        } else {
            atualizarGrade(grade, listaIngredientes);
        }
    }

    private HBox criarCaixaDeBusca(List<Ingrediente> listaIngredientes, GridPane grade, HBox caixasDeSelecao) {
        TextField campoBusca = new TextField();
        campoBusca.setPromptText("Buscar ingrediente");
        campoBusca.setStyle("-fx-font-size: 14pt; -fx-padding: 10px;");

        Button botaoBusca = criarBotaoBusca(listaIngredientes, grade, campoBusca, caixasDeSelecao);

        HBox caixaDeBusca = new HBox(10, campoBusca, botaoBusca, caixasDeSelecao);
        caixaDeBusca.setAlignment(Pos.CENTER);
        caixaDeBusca.setStyle("-fx-background-color: #f2f2f2; -fx-padding: 10px;");

        return caixaDeBusca;
    }

    private Button criarBotaoBusca(List<Ingrediente> listaIngredientes, GridPane grade, TextField campoBusca,
            HBox caixasDeSelecao) {
        Button botaoBusca = new Button("Buscar");
        botaoBusca.setStyle("-fx-background-color: #4a90e2; -fx-text-fill: #ffffff; -fx-font-size: 14pt;");

        botaoBusca.setOnAction(e -> lidarComBotaoBusca(listaIngredientes, grade, campoBusca));

        return botaoBusca;
    }

    private void lidarComBotaoBusca(List<Ingrediente> listaIngredientes, GridPane grade,
            TextField campoBusca) {
        String termoBusca = campoBusca.getText().toLowerCase();
        List<Ingrediente> listaFiltrada = listaIngredientes.stream()
                .filter(i -> i.getNome().toLowerCase().contains(termoBusca))
                .collect(Collectors.toList());
        atualizarGrade(grade, listaFiltrada);
    }

    private HBox criarResumoEstoque() {
        HBox resumoEstoque = new HBox(20);
        resumoEstoque.setAlignment(Pos.CENTER);
        resumoEstoque.setPadding(new Insets(20));

        Button botaoAdicionarIngrediente = criarBotaoAdicionarIngrediente();

        resumoEstoque.getChildren().addAll(botaoAdicionarIngrediente);

        return resumoEstoque;
    }

    private Button criarBotaoAdicionarIngrediente() {
        Button botaoAdicionarIngrediente = new Button("Adicionar Ingrediente");

        botaoAdicionarIngrediente.setStyle(
                "-fx-background-color: #4a90e2;"
                        + "-fx-text-fill: #ffffff;"
                        + "-fx-font-size: 14pt;"
                        + "-fx-font-weight: bold;");

        botaoAdicionarIngrediente.setOnAction(e -> lidarComBotaoAdicionarIngrediente());

        return botaoAdicionarIngrediente;
    }

    private void lidarComBotaoAdicionarIngrediente() {
        this.mostrarTelaIngrediente(null, true);
    }

    public void mostrarTelaIngrediente(Ingrediente ingrediente, boolean novoIngrediente) {
        Stage palco = new Stage();
        VBox vbox = criarTelaIngredienteVBox(ingrediente, novoIngrediente, palco);

        palco.setScene(new Scene(vbox));
        palco.show();
    }

    private VBox criarTelaIngredienteVBox(Ingrediente ingrediente, boolean novoIngrediente, Stage palco) {
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(20));
        vbox.setStyle("-fx-background-color: #f2f2f2;");

        Label rotulo = new Label(
                novoIngrediente ? "Criar Ingrediente" : "Reabastecer o ingrediente " + ingrediente.getNome());
        rotulo.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        vbox.getChildren().add(rotulo);

        TextField campoNome = criarCampoNome(ingrediente, novoIngrediente);
        if (novoIngrediente) {
            vbox.getChildren().add(campoNome);
        }

        TextField campoQuantidade = criarCampoQuantidade();
        vbox.getChildren().add(campoQuantidade);

        Button botaoAdicionar = criarBotaoAdicionar(ingrediente, novoIngrediente, palco, campoNome, campoQuantidade);
        vbox.getChildren().add(botaoAdicionar);

        Button botaoCancelar = criarBotaoCancelar(palco);
        vbox.getChildren().add(botaoCancelar);

        vbox.setAlignment(Pos.CENTER);

        return vbox;
    }

    private TextField criarCampoNome(Ingrediente ingrediente, boolean novoIngrediente) {
        TextField campoNome = new TextField();
        campoNome.setStyle("-fx-font-size: 14pt; -fx-padding: 10px;");
        if (novoIngrediente) {
            campoNome.setPromptText("Nome do ingrediente");
        } else {
            Label rotulo = new Label("Quantidade a adicionar de " + ingrediente.getNome());
            rotulo.setFont(Font.font("Arial", FontWeight.BOLD, 14));
            VBox vbox = new VBox(10);
            vbox.setAlignment(Pos.CENTER);
            vbox.getChildren().add(rotulo);
        }
        return campoNome;
    }

    private TextField criarCampoQuantidade() {
        TextField campoQuantidade = new TextField();
        campoQuantidade.setPromptText("Quantidade");
        campoQuantidade.setStyle("-fx-font-size: 14pt; -fx-padding: 10px;");
        return campoQuantidade;
    }

    private Button criarBotaoAdicionar(Ingrediente ingrediente, boolean novoIngrediente, Stage palco,
            TextField campoNome, TextField campoQuantidade) {
        Button botaoAdicionar = new Button(novoIngrediente ? "Criar" : "Adicionar");
        botaoAdicionar.setStyle("-fx-background-color: #4a90e2; -fx-text-fill: #ffffff; -fx-font-size: 14pt;");

        botaoAdicionar.setOnAction(
                e -> lidarComBotaoAdicionar(ingrediente, novoIngrediente, palco, campoNome, campoQuantidade));

        return botaoAdicionar;
    }

    private void lidarComBotaoAdicionar(Ingrediente ingrediente, boolean novoIngrediente, Stage palco,
            TextField campoNome, TextField campoQuantidade) {
        String nome = campoNome.getText();
        int quantidade = Integer.parseInt(campoQuantidade.getText());
        boolean sucesso;
        if (novoIngrediente) {
            sucesso = controlador.criarIngrediente(nome, quantidade);
        } else {
            sucesso = controlador.adicionarIngrediente(ingrediente, quantidade);
        }
        palco.close();

        this.atualizarInterface();

        // Mostrando uma caixa de diálogo com o retorno
        Alert alerta = new Alert(sucesso ? AlertType.INFORMATION : AlertType.ERROR);
        alerta.setTitle("Resultado da Operação");
        alerta.setHeaderText(novoIngrediente ? "Criar Ingrediente" : "Reabastecer Ingrediente");
        alerta.setContentText(sucesso ? "Operação realizada com sucesso!" : "Falha na operação!");
        alerta.showAndWait();
    }

    private Button criarBotaoCancelar(Stage palco) {
        Button botaoCancelar = new Button("Cancelar");
        botaoCancelar.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: #ffffff; -fx-font-size: 14pt;");
        botaoCancelar.setOnAction(e -> palco.close());
        return botaoCancelar;
    }

    private void atualizarInterface() {
        if (this.interfaceAtual instanceof VBox) {
            VBox vbox = (VBox) this.interfaceAtual;
            vbox.getChildren().setAll(this.show());
        }
    }

    private void adicionarNaGrade(GridPane grade, Ingrediente ingrediente, int col, int row) {
        Button botaoReabastecer = new Button("Reabastecer");

        botaoReabastecer.setStyle(
                "-fx-background-color: #4a90e2;"
                        + "-fx-text-fill: #ffffff;"
                        + "-fx-font-size: 12pt;");

        botaoReabastecer.setOnAction(e -> lidarComBotaoReabastecer(ingrediente));

        Label rotuloNome = new Label(ingrediente.getNome());
        rotuloNome.setStyle("-fx-font-size: 14pt;");
        rotuloNome.setWrapText(true);
        rotuloNome.setMaxWidth(200);
        rotuloNome.setAlignment(Pos.CENTER);

        Label rotuloQuantidade = new Label("Quantidade: " + String.valueOf(ingrediente.getQuantidade()));
        rotuloQuantidade.setStyle("-fx-font-size: 14pt;");
        rotuloQuantidade.setWrapText(true);
        rotuloQuantidade.setMaxWidth(200);
        rotuloQuantidade.setAlignment(Pos.CENTER);

        VBox vbox = new VBox(10, rotuloNome, rotuloQuantidade, botaoReabastecer);
        vbox.setAlignment(Pos.CENTER);
        vbox.setStyle("-fx-background-color: #f2f2f2; -fx-padding: 10px;");
        vbox.setPrefWidth(200);

        grade.add(vbox, col, row);
    }

    private void lidarComBotaoReabastecer(Ingrediente ingrediente) {
        this.mostrarTelaIngrediente(ingrediente, false);
        this.atualizarInterface();
    }
}
