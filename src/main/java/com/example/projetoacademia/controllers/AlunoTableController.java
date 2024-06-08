package com.example.projetoacademia.controllers;

import com.example.projetoacademia.domain.AlunoModel;
import com.example.projetoacademia.services.AlunoService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.stage.WindowEvent;
import javafx.util.Callback;

import java.io.IOException;
import java.util.List;

public class AlunoTableController {

    @FXML
    private TableView<AlunoModel> tableView;

    @FXML
    private Button cadastrarAluno;

    @FXML
    private TextField filterTextField;

    @FXML
    private TableColumn<AlunoModel, Integer> idColumn;

    @FXML
    private TableColumn<AlunoModel, String> nomeColumn;

    @FXML
    private TableColumn<AlunoModel, String> emailColumn;

    @FXML
    private TableColumn<AlunoModel, String> cpfColumn;

    @FXML
    private TableColumn<AlunoModel, String> dtNascColumn;

    @FXML
    private TableColumn<AlunoModel, String> telefoneColumn;

    @FXML
    private TableColumn<AlunoModel, String> enderecoColumn;

    private final AlunoService alunoService;

    private ObservableList<AlunoModel> listaOriginal;
    private FilteredList<AlunoModel> filteredData;

    public AlunoTableController() {
        this.alunoService = new AlunoService();
    }

    public void initialize() {

        listaOriginal = FXCollections.observableArrayList(alunoService.getAllAlunos());
        filteredData = new FilteredList<>(listaOriginal);

        // Configuração das colunas
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        nomeColumn.setCellValueFactory(cellData -> cellData.getValue().nomeProperty());
        emailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        cpfColumn.setCellValueFactory(cellData -> cellData.getValue().cpfProperty());
        dtNascColumn.setCellValueFactory(cellData -> cellData.getValue().dtNascimentoProperty().asString());
        telefoneColumn.setCellValueFactory(cellData -> cellData.getValue().telefoneProperty());
        enderecoColumn.setCellValueFactory(cellData -> cellData.getValue().enderecoProperty());

        TableColumn<AlunoModel, Void> editColumn = new TableColumn<>("");
        editColumn.setCellFactory(createEditButtonCellFactory());
        tableView.getColumns().add(editColumn);

        // Configuração da coluna de botão "Delete"
        TableColumn<AlunoModel, Void> deleteColumn = new TableColumn<>("");
        deleteColumn.setCellFactory(createDeleteButtonCellFactory());
        tableView.getColumns().add(deleteColumn);



        // Atualiza a tabela
        atualizarTabela();



        filterTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            // Obtém o texto do filtro e converte para minúsculas
            String filter = newValue.toLowerCase();

            // Aplica o filtro à lista de dados filtrados
            filteredData.setPredicate(aluno -> {
                // Verifica se algum dos campos do aluno contém o texto de filtro
                return aluno.getNome().toLowerCase().contains(filter) ||
                        aluno.getEmail().toLowerCase().contains(filter) ||
                        aluno.getCpf().toLowerCase().contains(filter) ||
                        aluno.getTelefone().toLowerCase().contains(filter) ||
                        aluno.getEndereco().toLowerCase().contains(filter);
            });

            // Atualiza a tabela com os dados filtrados
            tableView.setItems(filteredData);
        });
    }

    @FXML
    private void handleCadastrarAluno(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/projetoacademia/cadastroAlunoView.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            stage.setOnHiding((WindowEvent event1) -> atualizarTabela());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleAtualizarAluno(AlunoModel aluno) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/projetoacademia/editAlunoView.fxml"));
            Parent root = loader.load();

            AlunoEditController controller = loader.getController();

            controller.setAluno(aluno);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            stage.setOnHiding((WindowEvent event1) -> atualizarTabela());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Callback<TableColumn<AlunoModel, Void>, TableCell<AlunoModel, Void>> createDeleteButtonCellFactory() {
        return new Callback<TableColumn<AlunoModel, Void>, TableCell<AlunoModel, Void>>() {
            @Override
            public TableCell<AlunoModel, Void> call(final TableColumn<AlunoModel, Void> param) {
                return new TableCell<AlunoModel, Void>() {
                    private final Button deleteButton = new Button("Deletar \uD83D\uDDD1");

                    {
                        deleteButton.setStyle("-fx-background-color: #CB1313; -fx-text-fill: white;");
                        deleteButton.setOnAction(event -> {
                            AlunoModel aluno = getTableView().getItems().get(getIndex());
                            alunoService.excluirAluno(aluno);
                            atualizarTabela();
                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(deleteButton);
                        }
                    }
                };
            }
        };
    }

    private Callback<TableColumn<AlunoModel, Void>, TableCell<AlunoModel, Void>> createEditButtonCellFactory() {
        return new Callback<TableColumn<AlunoModel, Void>, TableCell<AlunoModel, Void>>() {
            @Override
            public TableCell<AlunoModel, Void> call(final TableColumn<AlunoModel, Void> param) {
                return new TableCell<AlunoModel, Void>() {
                    private final Button editButton = new Button("Editar ✎");

                    {
                        editButton.setStyle("-fx-background-color: #C88119; -fx-text-fill: white;");
                        editButton.setOnAction(event -> {
                            AlunoModel aluno = getTableView().getItems().get(getIndex());
                            // Abra a janela de edição para este aluno
                            handleAtualizarAluno(aluno);
                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(editButton);
                        }
                    }
                };
            }
        };
    }

    private void atualizarTabela() {
        // Recupera a lista atualizada de alunos do serviço
        List<AlunoModel> alunos = alunoService.getAllAlunos();

        // Atualiza a lista original com os novos dados
        listaOriginal.clear();
        listaOriginal.addAll(alunos);

        // Atualiza a lista filtrada com os novos dados
        filterTextField.setText(""); // Limpa o filtro
        filteredData.setPredicate(aluno -> true); // Exibe todos os alunos

        // Atualiza a tabela com os novos dados
        tableView.setItems(FXCollections.observableArrayList(alunos));
    }

    @FXML
    private void sairTable(ActionEvent event) {
        MenuItem menuItem = (MenuItem) event.getSource();
        ContextMenu menu = menuItem.getParentPopup();
        Stage stage = (Stage) menu.getOwnerWindow();
        stage.close();
    }
}
