create table vehicle
(
	id int auto_increment,
	brand VARCHAR(255) null,
	model VARCHAR(255) null,
	made int null,
	registration_nr VARCHAR(255) null,
	next_exam DATE null,
	constraint vehicle_pk
		primary key (id)
);

create unique index vehicle_registration_nr_uindex
	on vehicle (registration_nr);


create table employee
(
	id int auto_increment,
	name VARCHAR(255) null,
	surname VARCHAR(255) null,
	email VARCHAR(255) null,
	phone BIGINT null,
	note TEXT null,
	man_hour DOUBLE null,
	constraint employee_pk
		primary key (id)
);


create table customer
(
	id int auto_increment,
	name VARCHAR(255) null,
	surname VARCHAR(255) null,
	id_vehicle int null,
	birth_year DATE null,
	constraint customer_pk
		primary key (id),
	constraint customer_vehicle_id_fk
		foreign key (id_vehicle) references vehicle (id)
);


create table status
(
	id int auto_increment,
	stage VARCHAR(255) null,
	constraint status_pk
		primary key (id)
);


create table `order`
(
	id int auto_increment,
	receipt_date DATETIME null,
	planned_repair_date DATETIME null,
	start_repair_date DATETIME null,
	id_employee int null,
	problem_desc TEXT null,
	repair_desc TEXT null,
	id_status int null,
	id_vehicle int null,
	repair_cost DOUBLE null,
	parts_cost DOUBLE null,
	man_hour_cost DOUBLE null,
	number_man_hours int null,
	constraint order_pk
		primary key (id),
	constraint order_employee_id_fk
		foreign key (id_employee) references employee (id),
	constraint order_status_id_fk
		foreign key (id_status) references status (id),
	constraint order_vehicle_id_fk
		foreign key (id_vehicle) references vehicle (id)
);