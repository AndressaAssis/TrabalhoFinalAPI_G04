CREATE TABLE itemPedido (
id serial primary key,
quantidade INT NOT NULL,
precoUnitario NUMERIC NOT NULL,
valorLiquido NUMERIC NOT NULL,
percentualDesconto NUMERIC,
valorBruto NUMERIC NOT NULL,
pedido_id BIGINT,
FOREIGN KEY (pedido_id) REFERENCES pedido(id),
produto_id BIGINT,
FOREIGN KEY (produto_id) REFERENCES produto(id) 
);