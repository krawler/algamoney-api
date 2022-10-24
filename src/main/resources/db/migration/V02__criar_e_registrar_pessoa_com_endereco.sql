CREATE TABLE pessoa(
  id SERIAL PRIMARY KEY NOT NULL UNIQUE,
  nome VARCHAR(100) NOT NULL,
  ativo boolean NOT NULL,
  logradouro VARCHAR(100) NOT NULL,
  numero VARCHAR(100) NOT NULL,
  complemento VARCHAR(100),
  bairro VARCHAR(50) NOT NULL,
  cep VARCHAR(30) NOT NULL,
  cidade VARCHAR(100) NOT NULL,
  estado VARCHAR(40) NOT NULL
);

INSERT INTO pessoa("ativo", "bairro", "cep", "cidade", "complemento", "estado", "id", "logradouro", "nome", "numero") values (true, 'Jardim Brasilia', '18910-066', 'S C R Pardo', '', 'SP', 1, 'Romeu Jose Batista', 'Rafael A Ramos', '372');
INSERT INTO pessoa("ativo", "bairro", "cep", "cidade", "complemento", "estado", "id", "logradouro", "nome", "numero") values (true, 'Centro', '18950-010', 'Ourinhos', '', 'SP', 2, 'Carlos Farias da Silva Gomes', 'Gertrudes da silva Coelho', '1023');     