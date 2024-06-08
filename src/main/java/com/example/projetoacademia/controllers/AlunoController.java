package com.example.projetoacademia.controllers;

import com.example.projetoacademia.domain.AlunoModel;
import com.example.projetoacademia.services.AlunoService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.sql.Date;

public class AlunoController {

    @FXML
    private TextField nomeField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField cpfField;

    @FXML
    private DatePicker dtNascPicker;

    @FXML
    private TextField enderecoField;

    @FXML
    private TextField telefoneField;

    @FXML
    private Button cadastrarButton;

    @FXML
    private Button cancelarButton;

    private final AlunoService alunoService;

    public AlunoController() {
        this.alunoService = new AlunoService();
    }

    @FXML
    private void initialize() {
        cadastrarButton.setOnAction(event -> cadastrarAluno());
        cancelarButton.setOnAction(event -> cancelarAluno());
    }

    @FXML
    private void cadastrarAluno() {
        String nome = nomeField.getText();
        String email = emailField.getText();
        String cpf = cpfField.getText();
        String endereco = enderecoField.getText();
        String telefone = telefoneField.getText();
        LocalDate dtNasc = dtNascPicker.getValue();

        AlunoModel aluno = new AlunoModel(nome, email, cpf, endereco, telefone, Date.valueOf(dtNasc));

        int rowsAffected = alunoService.inserirAluno(aluno);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Cadastro de Aluno");

        if (rowsAffected > 0) {
            alert.setHeaderText("Aluno cadastrado com sucesso.");
        } else {
            alert.setHeaderText("Falha ao cadastrar aluno.");
        }

        alert.showAndWait();

        // Fecha a janela de cadastro
        Stage stage = (Stage) nomeField.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void cancelarAluno() {
        // Fecha a janela de edição do aluno
        Stage stage = (Stage) cancelarButton.getScene().getWindow();
        stage.close();
    }
}
