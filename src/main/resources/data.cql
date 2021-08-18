CREATE KEYSPACE IF NOT EXISTS tocomo WITH replication ={'class': 'SimpleStrategy', 'replication_factor':1};

USE tocomo;
CREATE TABLE IF NOT EXISTS customers(
	user_id UUID,
	user_name Text,
	name Text,
	trx_id timeuuid,
	PRIMARY KEY (user_name, name, user_id)
);

USE tocomo;
CREATE TABLE IF NOT EXISTS transactions(
	user_id int,
	user_name Text,
	name Text,
	client_id int,
	client_name Text,
	trx_type Text,
	status Boolean,
	trx_key timeuuid,
	amount Double,
	PRIMARY KEY ( user_id, user_name, trx_key)
);

INSERT INTO tocomo.customers
(user_id, user_name, name ,  trx_id)
VALUES(UUID() ,'zodiac254', 'Zadie Kessler' , now()) IF NOT EXISTS;

INSERT INTO tocomo.customers
(user_id, user_name, name ,  trx_id)
VALUES(UUID() , 'Andromeda18' , 'Andrew Media' , now()) IF NOT EXISTS;

INSERT INTO tocomo.customers
(user_id, user_name, name, trx_id)
VALUES(UUID() , 'Keplar274' , 'Kevin Polaris' , now()) IF NOT EXISTS;

INSERT INTO tocomo.customers
(user_id, user_name, name, trx_id)
VALUES(UUID() , 'sunsolaris81',  'Susan Olegara', now()) IF NOT EXISTS;

INSERT INTO tocomo.customers
(user_id, user_name, name , trx_id)
VALUES(UUID() , 'Mozbar52', 'Marco Sanchez' , now()) IF NOT EXISTS;

INSERT INTO tocomo.customers
(user_id, user_name, name , trx_id)
VALUES(UUID() , 'Acesbasis45', 'Jim Smith' , now()) IF NOT EXISTS;

INSERT INTO tocomo.customers
(user_id, user_name, name ,  trx_id)
VALUES(UUID() ,'zodiac254', 'Zadie Kessler' , now()) IF NOT EXISTS;

INSERT INTO tocomo.customers
(user_id, user_name, name ,  trx_id)
VALUES(UUID() ,'Andromeda28' , 'Ashton Medalia' , now()) IF NOT EXISTS;


INSERT INTO tocomo.transactions
( user_id, user_name,  name,  status,  trx_type, client_id, client_name,   trx_key, amount )
VALUES(0, 'zodiac254', 'Zadie Kessler', true ,  'Send',  0 , 'Marco Sanchez', now(), 200.00);

INSERT INTO tocomo.transactions
(user_id, user_name, name, status,  trx_type, client_id, client_name,   trx_key, amount )
VALUES(0, 'Andromeda18' , 'Andrew Media', true ,  'Send',  0 , 'Jim Smith', now(), 400.00);

INSERT INTO tocomo.transactions
(user_id, user_name, name, status,  trx_type, client_id, client_name,   trx_key, amount )
VALUES(0, 'Keplar274', 'Kevin Polaris' , true ,  'Send' ,  0 , 'Andrew Media', now(), 56.00);

INSERT INTO tocomo.transactions
(user_id, user_name, name, status,  trx_type, client_id, client_name,   trx_key, amount )
VALUES(0, 'sunsolaris81', 'Susan Olegara', true ,  'Send',  0 , 'Marco Sanchez', now(), 25.53);

INSERT INTO tocomo.transactions
(user_id, user_name, name, status,  trx_type, client_id, client_name,   trx_key, amount )
VALUES(0), 'Mozbar52', 'Marco Sanchez' , true ,  'Send' , 0 , 'Zadie Kessler' , now(), 1250.00);

INSERT INTO tocomo.transactions
(user_id, user_name, name, status,  trx_type, client_id, client_name,   trx_key, amount )
VALUES(0, 'Acesbasis45', 'Jim Smith' , true ,  'Send' ,  0 , 'Susan Olegara' , now(), 23.00);