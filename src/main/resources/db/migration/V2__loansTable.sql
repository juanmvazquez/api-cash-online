CREATE TABLE db_backend_cash.loan (
    id INT NOT NULL AUTO_INCREMENT UNIQUE,
    total DECIMAL (19,2) NOT NULL,
    user_id INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES user(user_id));