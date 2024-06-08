package com.example.projetoacademia.services;

import com.example.projetoacademia.domain.AlunoModel;
import com.example.projetoacademia.repositorio.AlunoRepository;

import java.sql.Date;
import java.util.List;

public class AlunoService {

    private final AlunoRepository alunoRepository;

    public AlunoService() {
        this.alunoRepository = new AlunoRepository();
    }

    public int inserirAluno(AlunoModel alunoModel) {
        return alunoRepository.inserirAluno(alunoModel);
    }

    public List<AlunoModel> getAllAlunos(){
        return alunoRepository.getAllAlunos();
    }

    public AlunoModel excluirAluno(AlunoModel alunoModel){
        return alunoRepository.excluirAluno(alunoModel);
    }

    public int atualizarAluno(AlunoModel alunoModel){
        return alunoRepository.atualizarAluno(alunoModel);
    }

}
