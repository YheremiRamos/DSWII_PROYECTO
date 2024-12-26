-- Cambia '192.168.56.1' por '%' para permitir desde cualquier IP si es seguro hacerlo.
CREATE USER 'root'@'192.168.56.1' IDENTIFIED BY 'mysql';
ALTER USER 'root'@'192.168.56.1' IDENTIFIED BY 'mysql';
GRANT ALL PRIVILEGES ON *.* TO 'root'@'192.168.56.1' WITH GRANT OPTION;
FLUSH PRIVILEGES;
