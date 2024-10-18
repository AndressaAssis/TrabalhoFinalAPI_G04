CREATE TABLE cliente (
id serial primary key,
nome VARCHAR (250) NOT NULL,
cpf VARCHAR (11) NOT NULL UNIQUE,
email VARCHAR (250) NOT NULL UNIQUE,
dataNascimento DATE
);
