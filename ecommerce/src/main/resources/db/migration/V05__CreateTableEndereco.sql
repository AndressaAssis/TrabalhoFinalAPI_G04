CREATE TABLE endereco {

id serial primary key,
cep VARCHAR(10),
rua VARCHAR(250),
bairro VARCHAR(250),
cidade VARCHAR(250),
numero INT,
complemento TEXT,
uf VARCHAR(250)
}