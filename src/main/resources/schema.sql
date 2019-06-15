create table notice_board (
	notice_id bigint auto_increment,
	title varchar(255) not null,
	content varchar(2048),
	created_by varchar(255) not null,
	created timestamp not null,
	updated_by varchar(255) not null,
	updated timestamp not null,
	primary key(notice_id)
);
