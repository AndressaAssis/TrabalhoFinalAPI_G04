CREATE TABLE endereco {

id serial primary key,
cep VARCHAR(10) NOT NULL,
rua VARCHAR(250) NOT NULL,
bairro VARCHAR(250) NOT NULL,
cidade VARCHAR(250) NOT NULL,
numero INT NOT NULL,
complemento TEXT,
uf VARCHAR(250)
}