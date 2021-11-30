drop table catandmouse; 

create table catandmouse(
username 	varchar(30),
password 	varbinary(16),
catwins	 	int,
catlosses 	int,
mousewins	int,
mouselosses	int);


alter table catandmouse
	add constraint users_username_pk primary key(username);
	
	
insert into catandmouse
	values('caleb', aes_encrypt('club', 'key'), 0, 0, 0, 0);
insert into catandmouse
	values('justin', aes_encrypt('justkrik', 'key'), 0, 0, 0, 0);
insert into catandmouse
	values('skyler', aes_encrypt('codingman', 'key'), 0, 0, 0, 0);
insert into catandmouse
	values('nick', aes_encrypt('nb', 'key'), 0, 0, 0, 0); 