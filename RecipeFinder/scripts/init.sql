create table Material(
id int not null primary key,
item char(100) not null,
unit Enum(of,grams,ml,slices),
date Date);