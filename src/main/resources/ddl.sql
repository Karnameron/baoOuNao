create table proposta (categoria tinyint check (categoria between 0 and 1), qtdlikes integer not null, id bigint not null auto_increment, user_id_id bigint not null, descricao varchar(255), titulo varchar(255), primary key (id)) engine=InnoDB;
create table usuario (limite_de_like integer not null, status_proposta bit not null, id bigint not null auto_increment, email varchar(255), nome varchar(255), senha varchar(255), sobrenome varchar(255), tipo varchar(255), primary key (id)) engine=InnoDB;
alter table proposta add constraint UK_s6lurtjdne53vxbdlnm8dg23 unique (user_id_id);
alter table proposta add constraint FKjd9emc4qh73nx9juyc8ktil0l foreign key (user_id_id) references usuario (id);
