create user 'dbuser' identified by 'dbpass';
grant all privileges on *.* to 'dbuser' with grant option;
flush privileges;

create schema gateway;
create schema service;