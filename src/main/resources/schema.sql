CREATE TABLE bank (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    bic VARCHAR(9) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE client (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    short_name VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    legal_form ENUM ('OOO', 'ZAO', 'IP') NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE deposit (
    id BIGINT NOT NULL AUTO_INCREMENT,
    client_id BIGINT NOT NULL,
    bank_id BIGINT NOT NULL,
    open_date DATE NOT NULL,
    percentage DOUBLE NOT NULL,
    term INT NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(client_id) REFERENCES client(id),
    FOREIGN KEY(bank_id) REFERENCES bank(id)
);