drop procedure if exists find_employees;
#
create procedure find_employees(in name_sample varchar(255))
begin
select count(id) from employee where name = name_sample;
end
#