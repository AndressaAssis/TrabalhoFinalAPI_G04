CREATE TABLE pedido (
id serial primary key,
dataPedido DATE NOT NULL,
valorTotal NUMERIC NOT NULL,
cliente_id BIGINT,
FOREIGN KEY (cliente_id) REFERENCES cliente(id) ON DELETE CASCADE
);
