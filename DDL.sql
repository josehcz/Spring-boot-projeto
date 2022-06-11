use petshop;

grant select, insert, delete, update on petshop.* to jose@'localhost';

create table usr_usuario (
  usr_id bigint unsigned not null auto_increment,
  usr_nome varchar(20) not null,
  usr_senha varchar(100) not null,
  primary key (usr_id),
  unique key uni_usuario_nome (usr_nome)
);

create table aut_autorizacao (
  aut_id bigint unsigned not null auto_increment,
  aut_nome varchar(20) not null,
  primary key (aut_id),
  unique key uni_aut_nome (aut_nome)
);

create table uau_usuario_autorizacao (
  usr_id bigint unsigned not null,
  aut_id bigint unsigned not null,
  primary key (usr_id, aut_id),
  foreign key aut_usuario_fk (usr_id) references usr_usuario (usr_id) on delete restrict on update cascade,
  foreign key aut_autorizacao_fk (aut_id) references aut_autorizacao (aut_id) on delete restrict on update cascade
);

create table cli_cliente (
  cli_id bigint unsigned not null auto_increment,
  cli_nome varchar(20) not null,
  cli_pet varchar(20) not null,
  cli_pedido varchar(20),
  cli_pedidocount bigint,
  cli_gasto bigint,
  primary key (cli_id)
);

create table produtos(
	prod_id bigint unsigned not null auto_increment,
    prod_nome varchar(20) not null,
    prod_valor bigint not null,
    primary key (prod_id)
);

create table reg_compras(
	regc_id bigint unsigned not null auto_increment,
    regc_compra varchar(100) not null,
    regc_valor bigint not null,
    primary key (regc_id)
);

create table reg_pedidos(
	regp_id bigint unsigned not null auto_increment,
    regp_pedido varchar(100) not null,
    regp_valor bigint not null,
    primary key (regp_id)
);

insert into usr_usuario (usr_nome, usr_senha)
    values ('admin', '$2a$10$i3.Z8Yv1Fwl0I5SNjdCGkOTRGQjGvHjh/gMZhdc3e7LIovAklqM6C');
insert into aut_autorizacao (aut_nome)
    values ('ROLE_ADMIN');
insert into uau_usuario_autorizacao values (1, 1);

insert into usr_usuario (usr_nome, usr_senha)
    values ('jose', 'jose@123');
select * from usr_usuario;
insert into uau_usuario_autorizacao values (2, 1);

insert into cli_cliente values ('1','cliente1','pet1','teste');


