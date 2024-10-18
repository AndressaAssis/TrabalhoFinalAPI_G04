CREATE TABLE cliente {

id serial primary key,
nome VARCHAR (250) NOT NULL,
cpf VARCHAR (11) NOT NULL,
telefone VARCHAR (13),
FOREIGN KEY (endereco_id) REFERENCES endereco(id),
email VARCHAR (250),
dataNascimento LOCAL DATE
}