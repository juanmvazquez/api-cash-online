CREATE TABLE db_backend_cash.user(
    user_id INT NOT NULL AUTO_INCREMENT UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    PRIMARY KEY (user_id));