
create table if not exists tbl_teacher(
	tea_id serial primary key,
	tea_name varchar(100) not null,
	tea_document varchar(20) not null,
	tea_status boolean default true
);

INSERT INTO tbl_teacher (tea_name, tea_document, tea_status) VALUES 
('John Smith', '1234567890', true),
('Alice Johnson', '0987654321', true),
('Michael Brown', '4567890123', true),
('Emily Davis', '9876543210', true),
('Christopher Lee', '6543210987', true),
('Jessica Wilson', '3210987654', true),
('David Martinez', '7890123456', true),
('Jennifer Taylor', '2345678901', true),
('Daniel Rodriguez', '8901234567', true),
('Sarah Clark', '5678901234', true);


create table if not exists tbl_subjects(
    sub_id serial primary key,
    tea_id int not null,
    sub_name varchar(50) not null,
    sub_status boolean default true,
    foreign key(tea_id) references tbl_teacher(tea_id)
);


INSERT INTO tbl_subjects (tea_id, sub_name, sub_status) VALUES 
(1, 'Matemáticas', true),
(2, 'Física', true),
(3, 'Literatura', true),
(4, 'Historia', true),
(5, 'Biología', true),
(6, 'Química', true),
(7, 'Ciencias de la Computación', true),
(8, 'Inglés', true),
(9, 'Arte', true),
(10, 'Música', true);


create table if not exists tbl_students (
	stu_id SERIAL,
	stu_document int primary key,
	stu_name varchar(100) not null,
	stu_phone varchar(20),
	stu_age smallint not null,
	stu_sex varchar(12) not null,
	stu_status boolean default true
);

INSERT INTO tbl_students (stu_document, stu_name, stu_phone, stu_age, stu_sex)
VALUES
    (123456789, 'John Doe', '123-456-7890', 20, 'Masculino'),
    (987654321, 'Jane Smith', '987-654-3210', 22, 'Feminino'),
    (456789123, 'Michael Johnson', '456-789-1230', 21, 'Masculino'),
    (789123456, 'Emily Davis', '789-123-4560', 19, 'Feminino'),
    (321654987, 'Chris Wilson', '321-654-9870', 20, 'Masculino'),
    (654987321, 'Sarah Brown', '654-987-3210', 23, 'Feminino'),
    (234567891, 'David Lee', '234-567-8910', 22, 'Masculino'),
    (876543219, 'Jessica Taylor', '876-543-2190', 20, 'Feminino'),
    (567891234, 'Matthew Martinez', '567-891-2340', 21, 'Masculino'),
    (345678912, 'Amanda Anderson', '345-678-9120', 19, 'Feminino'),
    (987654123, 'Brian White', '987-654-1230', 20, 'Masculino'),
    (654321987, 'Laura Garcia', '654-321-9870', 22, 'Feminino'),
    (876543210, 'Kevin Rodriguez', '876-543-2100', 21, 'Masculino'),
    (987654999, 'Melissa Hernandez', '987-654-3210', 20, 'Feminino'),
    (444325555, 'Christopher Perez', '123-456-7890', 19, 'Masculino'),
    (549222912, 'Stephanie Gonzalez', '345-678-9120', 22, 'Feminino'),
    (343481234, 'Justin Flores', '567-891-2340', 21, 'Masculino'),
    (876549999, 'Nicole Chavez', '876-543-2190', 20, 'Feminino'),
    (344444143, 'Brandon Rivera', '987-654-1230', 23, 'Masculino'),
    (654399990, 'Rachel Adams', '654-321-9870', 20, 'Feminino');


create table if not exists tbl_group_list(
	gro_id serial primary key,
	gro_name varchar(100) not null,
	gro_status boolean default true
);

	
CREATE OR REPLACE FUNCTION create_tables() RETURNS VOID AS $$
DECLARE
    name_table TEXT;
    i INT := 1;
BEGIN
    WHILE i <= 3 LOOP
        name_table := 'tbl_cluster_' || i;
        EXECUTE FORMAT('
					CREATE TABLE %I (
						stu_document int not null,
						note_1 numeric,
						note_2 numeric,
						note_3 numeric,
						final_note numeric,
						status boolean default false,
						id serial primary key,						
						foreign key (stu_document) references tbl_students(stu_document)
					)', name_table);
				
		EXECUTE FORMAT('INSERT INTO tbl_group_list(gro_name) VALUES (%L)', name_table);
        i := i + 1;
    END LOOP;
END;
$$ LANGUAGE plpgsql;

select create_tables();


create table if not exists tbl_students_group(
	stu_id serial primary key,
	tea_id int not null,
	gro_id int not null,
	stu_document int not null,
	foreign key (tea_id) references tbl_teacher(tea_id),
	foreign key (gro_id) references tbl_group_list(gro_id),
	foreign key (sub_id) references tbl_subjects(sub_id),
	foreign key (stu_document) references tbl_students(stu_document)
);



create table if not exists tbl_upload(
	upl_id serial primary key,
	tea_document varchar(30) not null,
	sub_id int not null,
	gro_id int not null,
	upl_name_document varchar(100) not null,
	upl_name_cloud varchar(200) not null,
	upl_status boolean default false,
	foreign key (sub_id) references tbl_subjects(sub_id),
	foreign key (gro_id) references tbl_group_list(gro_id)
);

