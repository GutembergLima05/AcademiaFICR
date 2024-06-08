package com.example.projetoacademia.controllers;

import com.example.projetoacademia.domain.AlunoModel;
import com.example.projetoacademia.services.AlunoService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Date;
import java.time.LocalDate;

public class AlunoEditController {

    @FXML
    private TextField idField;

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
    private Button atualizarButton;

    @FXML
    private Button cancelarButton;

    private final AlunoService alunoService;
    private AlunoModel aluno;

    public AlunoEditController() {
        this.alunoService = new AlunoService();
    }

    @FXML
    private void initialize() {
        atualizarButton.setOnAction(event -> atualizarAluno());
        cancelarButton.setOnAction(event -> cancelarAluno());
    }

    @FXML
    private void atualizarAluno() {
        String nome = nomeField.getText();
        String email = emailField.getText();
        String cpf = cpfField.getText();
        String endereco = enderecoField.getText();
        String telefone = telefoneField.getText();
        LocalDate dtNasc = dtNascPicker.getValue();
        int id = Integer.parseInt(idField.getText());

        System.out.println(id);

        AlunoModel aluno = new AlunoModel(id,nome, email, cpf, endereco, telefone, Date.valueOf(dtNasc));

        int rowsAffected = alunoService.atualizarAluno(aluno);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Atualização de Aluno");

        if (rowsAffected > 0) {
            alert.setHeaderText("Aluno atualizado com sucesso.");
        } else {
            alert.setHeaderText("Falha ao atualizar aluno.");
        }

        alert.showAndWait();

        Stage stage = (Stage) nomeField.getScene().getWindow();
        stage.close();
    }

    public void setAluno(AlunoModel aluno) {
        this.aluno = aluno;

        idField.setText(String.valueOf(aluno.getId()));
        nomeField.setText(aluno.getNome());
        emailField.setText(aluno.getEmail());
        cpfField.setText(aluno.getCpf());

        if (aluno.getDtNascimento() != null) {
            dtNascPicker.setValue(aluno.getDtNascimento().toLocalDate());
        }

        enderecoField.setText(aluno.getEndereco());
        telefoneField.setText(aluno.getTelefone());
    }

    @FXML
    private void cancelarAluno() {

        Stage stage = (Stage) cancelarButton.getScene().getWindow();
        stage.close();
    }
}
