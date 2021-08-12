CREATE KEYSPACE tocomo IF NOT EXISTS WITH replication ={'class': 'simpleStrategy', 'replication_factor':1};

USE tocomo;
CREATE TABLE customers IF NOT EXISTS(
userid UUID,
name text,
type text,
amount Double,
status Boolean,
PRIMARY KEY (id,name),
);

--insert some data
INSERT INTO tocomo.customers
(userid, name, type, amount, status)
VALUES(UUID(), Leonard, Fiat, toTimeStamp(now()));