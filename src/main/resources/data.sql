INSERT INTO department (name) VALUES ('Department of Redundancy Department');
INSERT INTO department (name) VALUES ('Network Administration');
INSERT INTO department (name) VALUES ('Research and Development');
INSERT INTO department (name) VALUES ('Store Support');

INSERT INTO project (name, from_date, to_date) VALUES ('Project X', '1961-03-01', '2002-08-31');
INSERT INTO project (name) VALUES ('Forlorn Cupcake');
INSERT INTO project (name, from_date) VALUES ('The Never-ending Project', '2010-09-01');
INSERT INTO project (name, to_date) VALUES ('Absolutely Done By', '2020-06-30');
INSERT INTO project (name, from_date, to_date) VALUES ('Royal Shakespeare', '2015-10-15', '2016-03-14');
INSERT INTO project (name, from_date, to_date) VALUES ('Plan 9', '2014-10-01', '2020-12-31');

INSERT INTO employee (first_name, last_name, birth_date, hire_date)
VALUES ('Fredrick', 'Keppard', '1953-07-15', '2001-04-01');
INSERT INTO employee (department_id, first_name, last_name, birth_date, hire_date)
VALUES (1, 'Flo', 'Henderson', '1990-12-28', '2011-08-01');
INSERT INTO employee (department_id, first_name, last_name, birth_date, hire_date)
VALUES (2, 'Franklin', 'Trumbauer', '1980-07-14', '1998-09-01');
INSERT INTO employee (department_id, first_name, last_name, birth_date, hire_date)
VALUES (2, 'Delora', 'Coty', '1976-07-04', '2009-03-01');
INSERT INTO employee (department_id, first_name, last_name, birth_date, hire_date)
VALUES (2, 'Sid', 'Goodman', '1972-06-04', '1998-09-01');
INSERT INTO employee (department_id, first_name, last_name, birth_date, hire_date)
VALUES (3, 'Mary Lou', 'Wolinski', '1983-04-08', '2012-04-01');
INSERT INTO employee (department_id, first_name, last_name, birth_date, hire_date)
VALUES (3, 'Jammie', 'Mohl', '1987-11-11', '2013-02-16');
INSERT INTO employee (department_id, first_name, last_name, birth_date, hire_date)
VALUES (3, 'Neville', 'Zellers', '1983-04-04', '2013-07-01');
INSERT INTO employee (department_id, first_name, last_name, birth_date, hire_date)
VALUES (3, 'Meg', 'Buskirk', '1987-05-13', '2007-11-01');
INSERT INTO employee (department_id, first_name, last_name, birth_date, hire_date)
VALUES (3, 'Matthew', 'Duford', '1968-04-05', '2016-02-16');
INSERT INTO employee (department_id, first_name, last_name, birth_date, hire_date)
VALUES (4, 'Jarred', 'Lukach', '1981-09-25', '2003-05-01');
INSERT INTO employee (department_id, first_name, last_name, birth_date, hire_date)
VALUES (4, 'Gabreila', 'Christie', '1980-03-17', '1999-08-01');