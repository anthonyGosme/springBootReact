#h2
jdbc url : jdbc:h2:mem:testdb
usrnam sa
#mariadb

## clean ##
brew services stop mariadb
kill -9  mysql
ps aux | grep mar
rm -rf  /usr/local/var/mysql


## INSTALL ##
brew install mariadb
mysql.server start

## CREATE USER ##
sudo mysql -u root -p
CREATE USER 'dbuser'@'localhost' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON *.* TO 'dbuser'@'localhost' ;
select host, user, password from mysql.user;
exit
mysql -u dbuser -ppassword
mysql.server status --user=dbuser

## beaver ##
localhost 3306
dbuser
password
create dabatabase cardb


