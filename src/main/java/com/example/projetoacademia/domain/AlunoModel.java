package com.example.projetoacademia.domain;

import javafx.beans.property.*;

import java.sql.Date;

public class AlunoModel {

    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty nome = new SimpleStringProperty();
    private final StringProperty email = new SimpleStringProperty();
    private final StringProperty cpf = new SimpleStringProperty();
    private final ObjectProperty<Date> dtNascimento = new SimpleObjectProperty<>();
    private final StringProperty telefone = new SimpleStringProperty();
    private final StringProperty endereco = new SimpleStringProperty();
    
    public AlunoModel(){}

    public AlunoModel(int id, String nome, String email, String cpf,String endereco , String telefone, Date dtNascimento) {
        this.id.set(id);
        this.nome.set(nome);
        this.email.set(email);
        this.cpf.set(cpf);
        this.dtNascimento.set(dtNascimento);
        this.telefone.set(telefone);
        this.endereco.set(endereco);
    }

    public AlunoModel(String nome, String email, String cpf,String endereco , String telefone, Date dtNascimento) {
        this.nome.set(nome);
        this.email.set(email);
        this.cpf.set(cpf);
        this.dtNascimento.set(dtNascimento);
        this.telefone.set(telefone);
        this.endereco.set(endereco);
    }


    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public String getNome() {
        return nome.get();
    }

    public void setNome(String nome) {
        this.nome.set(nome);
    }

    public StringProperty nomeProperty() {
        return nome;
    }


    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public StringProperty emailProperty() {
        return email;
    }

    public String getCpf() {
        return cpf.get();
    }

    public void setCpf(String cpf) {
        this.cpf.set(cpf);
    }

    public StringProperty cpfProperty() {
        return cpf;
    }

    public Date getDtNascimento() {
        return dtNascimento.get();
    }

    public void setDtNascimento(Date dtNascimento) {
        this.dtNascimento.set(dtNascimento);
    }

    public ObjectProperty<Date> dtNascimentoProperty() {
        return dtNascimento;
    }


    public String getTelefone() {
        return telefone.get();
    }

    public void setTelefone(String telefone) {
        this.telefone.set(telefone);
    }

    public StringProperty telefoneProperty() {
        return telefone;
    }


    public String getEndereco() {
        return endereco.get();
    }

    public void setEndereco(String endereco) {
        this.endereco.set(endereco);
    }

    public StringProperty enderecoProperty() {
        return endereco;
    }
}