create table address (id bigint not null auto_increment, city varchar(255), line1 varchar(255),
    zip varchar(255), employee_id bigint, primary key (id));
create table employee (id bigint not null auto_increment, name varchar(255), salary integer not null,
    saved_at datetime, primary key (id));
alter table address add constraint fk_employee foreign key (employee_id) references employee (id);
