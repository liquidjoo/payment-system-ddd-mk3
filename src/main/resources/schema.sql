drop table if exists product;
create table product(
    product_id bigint primary key auto_increment,
    name varchar (150) not null,
    status varchar (20) not null
);

drop table if exists plan;
create table plan(
    plan_id bigint primary key auto_increment,
    product_id bigint null ,
    plan_name varchar(200) not null ,
    plan_description varchar(500) not null ,
    plan_price int not null
);

drop table if exists option_group_spec;
create table option_group_spec(
    option_group_spec_id bigint primary key auto_increment ,
    name varchar(100) not null ,
    plan_id bigint null ,
    exclusive bit not null ,
    basic bit not null
);

drop table if exists option_spec;
create table option_spec(
    option_spec_id bigint primary key auto_increment ,
    option_group_spec_id bigint null ,
    name varchar(100) not null ,
    price int not null
);

drop table if exists `order`;
create table `order`(
    order_id bigint primary key auto_increment,
    project_id varchar(255) not null ,
    product_id bigint not null ,
    ordered_time timestamp not null ,
    status varchar(30) not null
);

drop table if exists order_line_item;
create table order_line_item(
    order_line_item_id bigint primary key auto_increment,
    order_id bigint null ,
    plan_id binary not null ,
    plan_name varchar(200) not null
);

drop table if exists order_option_group;
create table order_option_group(
    order_option_group_id bigint primary key auto_increment,
    order_line_item_id bigint null ,
    name varchar(100) not null
);

drop table if exists order_option;
create table order_option(
    order_option_group_id bigint not null ,
    name varchar(100) not null ,
    price int not null ,
    constraint pk_order_options primary key (order_option_group_id, name, price)
);
