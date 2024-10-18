CREATE TABLE cliente {

id serial primary key,
nome VARCHAR (250),
cpf VARCHAR (11),
telefone VARCHAR (13),
FOREIGN KEY (endereco_id) REFERENCES endereco(id),
email VARCHAR (250),
dataNascimento LOCAL DATE
}