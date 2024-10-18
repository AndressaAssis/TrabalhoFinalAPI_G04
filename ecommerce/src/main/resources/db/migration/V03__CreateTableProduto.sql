CREATE TABLE  produto{


id serial primary key,
nome VARCHAR (100),
genero VARCHAR (100),
plataforma VARCHAR (100),
precoUnitario DECIMAL,
descricao TEXT,
quantidadeEstoque INT,
dataCadastro LOCAL DATE
}