CREATE TABLE endereco (
id serial primary key,
cep VARCHAR(10) NOT NULL,
logradouro VARCHAR(250) NOT NULL,
bairro VARCHAR(250) NOT NULL,
cidade VARCHAR(250) NOT NULL,
numero INT NOT NULL,
complemento TEXT,
uf VARCHAR(2),
cliente_id BIGINT,
FOREIGN KEY (cliente_id) REFERENCES cliente(id)
);
