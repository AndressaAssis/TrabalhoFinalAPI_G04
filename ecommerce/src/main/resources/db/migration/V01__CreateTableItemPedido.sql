CREATE TABLE pedido{

id serial primary key,
dataPedido LOCAL DATE NOT NULL,
valorLiquido NUMERIC NOT NULL,
FOREIGN KEY (cliente_id) REFERENCES cliente(id),
percentualDesconto INT,
FOREIGN KEY (itemPedido_id) REFERENCES itemPedido(id),
valorBruto NUMERIC NOT NULL

}