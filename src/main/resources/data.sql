INSERT INTO bank (name, bic) VALUES ('Sberbank', '853876431');
INSERT INTO bank (name, bic) VALUES ('T-Bank', '943235293');
INSERT INTO bank (name, bic) VALUES ('VTB', '743823838');

INSERT INTO client (name, shortName, address, legalForm) VALUES('2GIS', '2G', 'Moscow, Marksistskaya, 3', 'OOO');
INSERT INTO client (name, shortName, address, legalForm) VALUES('Autoradio', 'AR', 'Moscow, Varshavskoe 9', 'OOO');
INSERT INTO client (name, shortName, address, legalForm) VALUES('Bakery 1', 'B1', 'Tchaikovsky, Sovetskaya 22', 'IP');
INSERT INTO client (name, shortName, address, legalForm) VALUES('Bread Factory 22', 'BF22', 'Moscow, Academician Pavlov 4', 'ZAO');

INSERT INTO deposit (clientId, bankId, openDate, percentage, term) VALUES (1, 2, '2023-01-21', 5, 12);
INSERT INTO deposit (clientId, bankId, openDate, percentage, term) VALUES (2, 1, '2023-03-14', 7, 12);
INSERT INTO deposit (clientId, bankId, openDate, percentage, term) VALUES (3, 2, '2023-05-28', 6.5, 12);
INSERT INTO deposit (clientId, bankId, openDate, percentage, term) VALUES (4, 3, '2023-07-07', 10, 12);