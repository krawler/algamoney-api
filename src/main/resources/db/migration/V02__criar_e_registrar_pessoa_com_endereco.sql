CREATE TABLE endereco(
	id SERIAL PRIMARY KEY NOT NULL UNIQUE,
	logradouro VARCHAR(100) NOT NULL,
  numero VARCHAR(100) NOT NULL,
  complemento VARCHAR(100),
  bairro VARCHAR(50) NOT NULL,
  cep VARCHAR(30) NOT NULL,
  cidade VARCHAR(100) NOT NULL,
  estado VARCHAR(40) NOT NULL
);