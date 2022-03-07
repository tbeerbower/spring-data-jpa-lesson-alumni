INSERT INTO department (department_id, name)
VALUES (1, 'Department 1'),
       (2, 'Department 2');

INSERT INTO project (project_id, name, from_date, to_date)
VALUES (1, 'Project 1', '2000-01-02', '2000-12-31'),
       (2, 'Project 2', NULL, NULL);

INSERT INTO employee (employee_id, department_id, first_name, last_name, birth_date, hire_date)
VALUES (1, 1, 'First1', 'Last1', '1981-01-01', '2001-01-02'),
       (2, 2, 'First2', 'Last2', '1982-02-01', '2002-02-03'),
       (3, 1, 'First3', 'Last3', '1983-03-01', '2003-03-04'),
       (4, 1, 'First4', 'Last4', '1984-04-01', '2004-04-05');

INSERT INTO project_employee (project_id, employee_id)
VALUES (1, 1),
       (2, 2),
       (2, 3);
