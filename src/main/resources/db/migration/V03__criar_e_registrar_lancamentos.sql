CREATE TABLE lancamento(
	id SERIAL PRIMARY KEY NOT NULL UNIQUE,
	descricao VARCHAR(50) NOT NULL,
	data_vencimento DATE NOT NULL,
	data_pagamento DATE,
	valor DECIMAL(10,2) NOT NULL,
	observacao VARCHAR(100),
	tipo VARCHAR(20) NOT NULL,
	codigo_categoria BIGINT(20) NOT NULL,
	codigo_pessoa BIGINT(20) NOT NULL,
	FOREIN KEY(id_categoria) REFERENCES categoria(id),
  	FOREIN KEY(id_pessoa) REFERENCES pessoa(id) 
);

INSERT INTO lancamento(descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) VALUES('Salário mensal', '2019-06-10', null,  6500.00, 'Distribuição de lucros', 1, 1);
INSERT INTO lancamento(descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) VALUES('Bahamas', '2019-12-02', '2019-12-02',  1002.00, 'Hotel', 2, 1);



INSERT INTO lancamento(descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) VALUES('Extra', '2019-03-10', '2019-030-10',  1010.32, null, 'RECEITA', 4, 1);