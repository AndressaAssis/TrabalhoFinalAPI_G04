CREATE TABLE cliente (
id serial primary key,
nome VARCHAR (250) NOT NULL,
cpf VARCHAR (11) NOT NULL UNIQUE,
email VARCHAR (250) NOT NULL UNIQUE,
dataNascimento DATE,
endereco_id BIGINT,
FOREIGN KEY (endereco_id) REFERENCES endereco(id)
);
