package com.example.projetoacademia.repositorio;

import com.example.projetoacademia.domain.AlunoModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlunoRepository {

    public int inserirAluno(AlunoModel alunoModel) {
        try (Connection conn = DbManager.conectarDB();
             PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO Aluno (nome, email, cpf, endereco, telefone, dtNasc) VALUES (?, ?, ?, ?, ?, ?)")) {

            preparedStatement.setString(1, alunoModel.getNome());
            preparedStatement.setString(2, alunoModel.getEmail());
            preparedStatement.setString(3, alunoModel.getCpf());
            preparedStatement.setString(4, alunoModel.getEndereco());
            preparedStatement.setString(5, alunoModel.getTelefone());
            preparedStatement.setDate(6, alunoModel.getDtNascimento());

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected;

        } catch (SQLException e) {

            e.printStackTrace();
            return 0;
        } catch (Exception e) {

            e.printStackTrace();
            return 0;
        }
    }

    public List<AlunoModel> getAllAlunos(){

        List<AlunoModel> alunos = new ArrayList<>();

        try(Connection conn = DbManager.conectarDB();
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM Aluno");
            ResultSet resultSet = preparedStatement.executeQuery()){

            while(resultSet.next()){
                AlunoModel aluno = new AlunoModel();
                aluno.setId(resultSet.getInt("id"));
                aluno.setNome(resultSet.getString("nome"));
                aluno.setEmail(resultSet.getString("email"));
                aluno.setCpf(resultSet.getString("cpf"));
                aluno.setEndereco(resultSet.getString("endereco"));
                aluno.setTelefone(resultSet.getString("telefone"));
                aluno.setDtNascimento(resultSet.getDate("dtNasc"));

                alunos.add(aluno);
            }

        } catch (SQLException e){
            e.printStackTrace();

        } catch (Exception e){
            e.printStackTrace();

        }

        return alunos;
    }

    public AlunoModel excluirAluno(AlunoModel alunoModel) {
        try (Connection conn = DbManager.conectarDB();
             PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM Aluno WHERE id = ?")) {

            preparedStatement.setInt(1, alunoModel.getId());


            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                return alunoModel;
            } else {
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int atualizarAluno(AlunoModel alunoModel){
        try(Connection conn = DbManager.conectarDB();
            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE Aluno SET Nome = ?, Email = ?, CPF = ?, Endereco = ?, Telefone = ?, dtNasc = ? WHERE id = ?")){

            preparedStatement.setString(1, alunoModel.getNome());
            preparedStatement.setString(2, alunoModel.getEmail());
            preparedStatement.setString(3, alunoModel.getCpf());
            preparedStatement.setString(4, alunoModel.getEndereco());
            preparedStatement.setString(5, alunoModel.getTelefone());
            preparedStatement.setDate(6, alunoModel.getDtNascimento());
            preparedStatement.setInt(7, alunoModel.getId());

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected;

        } catch (SQLException e){
            e.printStackTrace();

            return 0;
        }
    }

}
