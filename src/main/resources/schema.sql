CREATE TABLE bank (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    bic VARCHAR(9) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE client (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    shortName VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    legalForm ENUM ('OOO', 'ZAO', 'IP') NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE deposit (
    id BIGINT NOT NULL AUTO_INCREMENT,
    clientId BIGINT NOT NULL,
    bankId BIGINT NOT NULL,
    openDate DATE NOT NULL,
    percentage DOUBLE NOT NULL,
    term INT NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(clientId) REFERENCES client(id),
    FOREIGN KEY(bankId) REFERENCES bank(id)
);