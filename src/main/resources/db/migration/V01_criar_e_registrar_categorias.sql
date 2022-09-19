CREATE TABLE categoria(
	id SERIAL PRIMARY KEY NOT NULL UNIQUE,
	nome VARCHAR(50) NOT NULL
);

INSERT INTO categoria(nome) values('Lazer');
INSERT INTO categoria(nome) values('Alimentação');
INSERT INTO categoria(nome) values('Supermercado');
INSERT INTO categoria(nome) values('Farmacia');
INSERT INTO categoria(nome) values('Outros');