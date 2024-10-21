CREATE TABLE cliente (
    id serial primary key,
    nome VARCHAR(250) NOT NULL,
    cpf VARCHAR(11) NOT NULL UNIQUE,
    email VARCHAR(250) NOT NULL UNIQUE,
    dataNascimento DATE,
    endereco_id BIGINT UNIQUE,
    FOREIGN KEY (endereco_id) REFERENCES endereco(id) ON DELETE SET NULL -- chave estrangeira
);