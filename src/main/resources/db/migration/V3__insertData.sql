#Agrego datos en la tabla de user
INSERT INTO db_backend_cash.user (first_name, last_name, email) VALUES ("Pepe", "Argento","pepeargento@gmail.com");
INSERT INTO db_backend_cash.user (first_name, last_name, email) VALUES ("Moni", "Argento","moniargento@gmail.com");
INSERT INTO db_backend_cash.user (first_name, last_name, email) VALUES ("Coqui", "Argento","coquiargento@gmail.com");
INSERT INTO db_backend_cash.user (first_name, last_name, email) VALUES ("Paola", "Argento","paolaargento@gmail.com");

#Agrego datos en la tabla de loan
INSERT INTO db_backend_cash.loan(total, user_id) VALUES(1000, 1);
INSERT INTO db_backend_cash.loan(total, user_id) VALUES(500, 1);
INSERT INTO db_backend_cash.loan(total, user_id) VALUES(2500, 1);
INSERT INTO db_backend_cash.loan(total, user_id) VALUES(5000, 2);
INSERT INTO db_backend_cash.loan(total, user_id) VALUES(1000, 2);
INSERT INTO db_backend_cash.loan(total, user_id) VALUES(2500, 3);
INSERT INTO db_backend_cash.loan(total, user_id) VALUES(5500, 4);
INSERT INTO db_backend_cash.loan(total, user_id) VALUES(3500, 4);