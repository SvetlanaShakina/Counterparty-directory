DROP DATABASE IF EXISTS counterparty_db;
CREATE DATABASE counterparty_db;
USE counterparty_db;

CREATE TABLE counterparty (
id INT auto_increment NOT NULL,
name VARCHAR(20) NOT NULL,
inn VARCHAR(10) NOT NULL,
kpp VARCHAR(9) NOT NULL,
bic VARCHAR(9) NOT NULL,
account_number VARCHAR(20) NOT NULL,
PRIMARY KEY (id),
UNIQUE (name)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

INSERT INTO counterparty (name, inn, kpp, bic, account_number)
VALUES ('УКСервис', '5501203515', '550701001', '045004774', '40702810023050003212');

INSERT INTO counterparty (name, inn, kpp, bic, account_number)
VALUES ('ООО Апельсин', '5504097128', '550401001', '045209673', '40702810045370100747');

INSERT INTO counterparty (name, inn, kpp, bic, account_number)
VALUES ('ООО Полет', '5504097128', '550401001', '045209673', '40702810045370100747');

INSERT INTO counterparty (name, inn, kpp, bic, account_number)
VALUES ('Модус', '5503219197', '643328632', '045209673', '40702810945000002493');

INSERT INTO counterparty (name, inn, kpp, bic, account_number)
VALUES ('ОЭК', '5503248039', '550401001', '045209673', '40702810445000093711');

INSERT INTO counterparty (name, inn, kpp, bic, account_number)
VALUES ('РТС', '5503249258', '550301001', '044525823', '40702810500000022738');

INSERT INTO counterparty (name, inn, kpp, bic, account_number)
VALUES ('Роснефть', '7706107510', '770601001', '044525716', '40702810656000001084');

INSERT INTO counterparty (name, inn, kpp, bic, account_number)
VALUES ('Сбер', '7707083893', '773601001', '044525225', '40702810023050003212');

INSERT INTO counterparty (name, inn, kpp, bic, account_number)
VALUES ('Газпром', '7736050003', '781043004', '044525823', '40702810300000000468');

INSERT INTO counterparty (name, inn, kpp, bic, account_number)
VALUES ('Магнит', '2309085638', '231101001', '040349602', '40702810330000100078');

INSERT INTO counterparty (name, inn, kpp, bic, account_number)
VALUES ('Феникс', '5503191784', '550301001', '044525974', '40702810900000000452');