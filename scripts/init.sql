CREATE TABLE categoria (
  id INT NOT NULL AUTO_INCREMENT,
  descricao VARCHAR(60) NULL,
  PRIMARY KEY (id)
);

CREATE TABLE produto (
  id INT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(60) NULL,
  PRIMARY KEY (id)
);

CREATE TABLE produto_categoria (
  produto_id INT NOT NULL,
  categoria_id INT NOT NULL,
  PRIMARY KEY (produto_id, categoria_id),
  FOREIGN KEY (produto_id) REFERENCES produto (id),
  FOREIGN KEY (categoria_id) REFERENCES categoria (id)
);

CREATE TABLE usuario (
  id INT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(30) NULL,
  email VARCHAR(255) NOT NULL,
  senha TEXT NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY UQ_email (email)
);

CREATE TABLE compra (
  id INT NOT NULL AUTO_INCREMENT,
  usuario_id INT NOT NULL,
  data_compra DATE NULL,
  parcelas INT NULL,
  tipo_pagamento TEXT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (usuario_id) REFERENCES usuario (id)
);

CREATE TABLE item_compra (
  id INT NOT NULL AUTO_INCREMENT,
  produto_id INT NOT NULL,
  compra_id INT NOT NULL,
  valor DECIMAL(10,2) NULL,
  quantidade INT NULL,
  devolucao BOOLEAN NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (produto_id) REFERENCES produto (id),
  FOREIGN KEY (compra_id) REFERENCES compra (id)
);
