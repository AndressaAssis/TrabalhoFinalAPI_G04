CREATE TABLE itemPedido {

id serial primary key,
quantidade INT,
precoUnitario DECIMAL,
valorBruto DECIMAL,
FOREIGN KEY (pedido_id) REFERENCES pedido(id),
FOREIGN KEY (produto_id) REFERENCES produto(id)

}