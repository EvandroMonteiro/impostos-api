create table pessoa (

codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
nome VARCHAR(50) NOT NULL,
ativo BOOLEAN NOT NULL,
logradouro VARCHAR(50) NULL,
numero  VARCHAR(50) NULL,
complemento VARCHAR(50) NULL,
bairro VARCHAR(50) NULL,
cep VARCHAR(50) NULL,
cidade VARCHAR(50) NULL,
estado VARCHAR(50) NULL

)ENGINE=InnoDB default charset=utf8;

INSERT INTO pessoa (nome,ativo,logradouro,numero,complemento,bairro,cep,cidade,estado) values ('Chris',true,'Rua do Amor','036','2ª Rua','Fundação Popular','38.400-121','São Tomé','Agua Grande');
INSERT INTO pessoa (nome,ativo,logradouro,numero,complemento,bairro,cep,cidade,estado) values ('Karen',true,'Rua do Amor','036','2ª Rua','Fundação Popular','11.400-121','São Tomé','Agua Grande');
INSERT INTO pessoa (nome,ativo,logradouro,numero,complemento,bairro,cep,cidade,estado) values ('Celmira',true,'Rua do Amor','036','2ª Rua','Fundação Popular','54.212-121','São Tomé','Agua Grande');
INSERT INTO pessoa (nome,ativo,logradouro,numero,complemento,bairro,cep,cidade,estado) values ('Alex',true,'Rua do Amor','036','2ª Rua','Fundação Popular','38.400-12','São Tomé','Agua Grande');
INSERT INTO pessoa (nome,ativo,logradouro,numero,complemento,bairro,cep,cidade,estado) values ('Paulinha',true,'Rua do Amor','036','2ª Rua','Fundação Popular','56.400-121','São Tomé','Agua Grande');
INSERT INTO pessoa (nome,ativo,logradouro,numero,complemento,bairro,cep,cidade,estado) values ('Jay',true,'Rua do Amor','036','2ª Rua','Fundação Popular','77.400-121','São Tomé','Agua Grande');
INSERT INTO pessoa (nome,ativo,logradouro,numero,complemento,bairro,cep,cidade,estado) values ('Mirian',true,'Rua do Amor','036','2ª Rua','Fundação Popular','12.400-121','São Tomé','Agua Grande');
INSERT INTO pessoa (nome,ativo,logradouro,numero,complemento,bairro,cep,cidade,estado) values ('Katy',true,'Rua do Amor','036','2ª Rua','Fundação Popular','31.400-121','São Tomé','Agua Grande');
INSERT INTO pessoa (nome,ativo,logradouro,numero,complemento,bairro,cep,cidade,estado) values ('Erikson',true,'Rua do Amor','036','2ª Rua','Fundação Popular','38.400-00','São Tomé','Agua Grande');
INSERT INTO pessoa (nome,ativo,logradouro,numero,complemento,bairro,cep,cidade,estado) values ('Barroso',true,'Rua do Amor','036','2ª Rua','Fundação Popular','99.400-121','São Tomé','Agua Grande');
INSERT INTO pessoa (nome,ativo,logradouro,numero,complemento,bairro,cep,cidade,estado) values ('Ana Paula',true,'Rua do Amor','036','2ª Rua','Fundação Popular','56.400-121','São Tomé','Agua Grande');
INSERT INTO pessoa (nome,ativo,logradouro,numero,complemento,bairro,cep,cidade,estado) values ('Mila',true,'Rua do Amor','036','2ª Rua','Fundação Popular','56.400-121','São Tomé','Agua Grande');
INSERT INTO pessoa (nome,ativo,logradouro,numero,complemento,bairro,cep,cidade,estado) values ('Butica',true,'Rua do Amor','036','2ª Rua','Fundação Popular','38.400-00','São Tomé','Agua Grande');
INSERT INTO pessoa (nome,ativo,logradouro,numero,complemento,bairro,cep,cidade,estado) values ('Admin',true,'Rua do Amor','036','2ª Rua','Fundação Popular','99.400-121','São Tomé','Agua Grande');
INSERT INTO pessoa (nome,ativo,logradouro,numero,complemento,bairro,cep,cidade,estado) values ('Benfica',true,'Rua do Amor','036','2ª Rua','Fundação Popular','54.212-121','São Tomé','Agua Grande');
INSERT INTO pessoa (nome,ativo,logradouro,numero,complemento,bairro,cep,cidade,estado) values ('Sporting',true,'Rua do Amor','036','2ª Rua','Fundação Popular','99.400-121','São Tomé','Agua Grande');