CREATE TABLE  produto{


id serial primary key,
nome VARCHAR (100) NOT NULL,
genero VARCHAR (100) NOT NULL,
plataforma VARCHAR (100) NOT NULL,
precoUnitario NUMERIC NOT NULL,
descricao TEXT NOT NULL,
quantidadeEstoque INT NOT NULL,
dataCadastro LOCAL DATE
}