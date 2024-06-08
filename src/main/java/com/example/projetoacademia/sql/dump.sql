CREATE TABLE Aluno(
 id SERIAL PRIMARY KEY,
 nome VARCHAR(30),
 email VARCHAR(30),
 cpf VARCHAR(14),
 telefone VARCHAR(13),
 endereco VARCHAR(30),
  dtNasc DATE
);