CREATE TABLE itemPedido {

id serial primary key,
quantidade INT NOT NULL,
precoUnitario NUMERIC NOT NULL,
valorBruto NUMERIC NOT NULL,
FOREIGN KEY (pedido_id) REFERENCES pedido(id),
FOREIGN KEY (produto_id) REFERENCES produto(id)

}