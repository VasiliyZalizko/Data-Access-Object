create schema netology;

create table netology.CUSTOMERS(
id int not null primary key,
name varchar(255) not null,
surname varchar(255) not null,
age int check (age > 0),
phone_number varchar(255)
);

create table netology.ORDERS(
id int not null primary key,
date varchar(255) not null,
customer_id int not null,
product_name varchar(255),
amount int,
foreign key (customer_id) references netology.CUSTOMERS (id)
);

