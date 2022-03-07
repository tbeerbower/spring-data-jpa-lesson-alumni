package com.techelevator.projects.repositories;

import com.techelevator.projects.model.Employee;
import com.techelevator.projects.model.Project;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeRepositoryTest extends BaseRepositoryTest {

    private static final Employee EMPLOYEE_1 =
        new Employee(1L, 1L, "First1", "Last1",
            LocalDate.parse("1981-01-01"), LocalDate.parse("2001-01-02"));
    private static final Employee EMPLOYEE_2 =
        new Employee(2L, 2L, "First2", "Last2",
            LocalDate.parse("1982-02-01"), LocalDate.parse("2002-02-03"));
    private static final Employee EMPLOYEE_3 =
        new Employee(3L, 1L, "First3", "Last3",
            LocalDate.parse("1983-03-01"), LocalDate.parse("2003-03-04"));
    private static final Employee EMPLOYEE_4 =
        new Employee(4L, 1L, "First4", "Last4",
            LocalDate.parse("1984-04-01"), LocalDate.parse("2004-04-05"));


    @Test
    void findByFirstNameIgnoreCaseContainingAndLastNameIgnoreCaseContaining_exact_match() {
        List<Employee> employees =
            employeeRepository.findByFirstNameIgnoreCaseContainingAndLastNameIgnoreCaseContaining("First1", "Last1");
        assertEquals(1, employees.size());
        assertEquals(EMPLOYEE_1, employees.get(0));

        employees = employeeRepository.findByFirstNameIgnoreCaseContainingAndLastNameIgnoreCaseContaining("First2", "");
        assertEquals(1, employees.size());
        assertEquals(EMPLOYEE_2, employees.get(0));

        employees = employeeRepository.findByFirstNameIgnoreCaseContainingAndLastNameIgnoreCaseContaining("", "Last3");
        assertEquals(1, employees.size());
        assertEquals(EMPLOYEE_3, employees.get(0));
    }

    @Test
    void findByFirstNameIgnoreCaseContainingAndLastNameIgnoreCaseContaining_partial_match() {
        List<Employee> employees =
            employeeRepository.findByFirstNameIgnoreCaseContainingAndLastNameIgnoreCaseContaining("irst", "ast");
        assertEquals(4, employees.size());

        employees =
            employeeRepository.findByFirstNameIgnoreCaseContainingAndLastNameIgnoreCaseContaining("first", "last");
        assertEquals(4, employees.size());

        employees =
            employeeRepository.findByFirstNameIgnoreCaseContainingAndLastNameIgnoreCaseContaining("FIRST", "LAST");
        assertEquals(4, employees.size());

        employees =
            employeeRepository.findByFirstNameIgnoreCaseContainingAndLastNameIgnoreCaseContaining("", "");
        assertEquals(4, employees.size());
    }

    @Test
    void findByFirstNameIgnoreCaseContainingAndLastNameIgnoreCaseContaining_no_match() {
        List<Employee> employees =
            employeeRepository.findByFirstNameIgnoreCaseContainingAndLastNameIgnoreCaseContaining("TEST", "TEST");
        assertEquals(0, employees.size());

        employees =
            employeeRepository.findByFirstNameIgnoreCaseContainingAndLastNameIgnoreCaseContaining("TEST", "Last1");
        assertEquals(0, employees.size());

        employees =
            employeeRepository.findByFirstNameIgnoreCaseContainingAndLastNameIgnoreCaseContaining("First1", "TEST");
        assertEquals(0, employees.size());
    }

    @Test
    void findByProjects() {
        Project project = projectRepository.getById(1L);
        assertNotNull(project);
        List<Employee> employees = employeeRepository.findByProjects(project);
        assertEquals(1, employees.size());
        assertEquals(EMPLOYEE_1, employees.get(0));

        project = projectRepository.getById(2L);
        assertNotNull(project);
        employees = employeeRepository.findByProjects(project);
        assertEquals(2, employees.size());
        assertEquals(EMPLOYEE_2, employees.get(0));
        assertEquals(EMPLOYEE_3, employees.get(1));

        project = projectRepository.getById(99L);
        assertNotNull(project);
        employees = employeeRepository.findByProjects(project);
        assertEquals(0, employees.size());
    }

    @Test
    void findByProjects_add_employee() {
        Project project = projectRepository.getById(1L);
        assertNotNull(project);
        Employee employee =employeeRepository.getById(3L);
        project.getEmployees().add(employee);
        List<Employee>  employees = employeeRepository.findByProjects(project);
        assertEquals(2, employees.size());
        assertEquals(EMPLOYEE_3, employees.get(1));
    }

    @Test
    void findByProjects_remove_employee() {
        Project project = projectRepository.getById(2L);
        assertNotNull(project);
        Employee employee =employeeRepository.getById(3L);
        project.getEmployees().remove(employee);
        List<Employee>  employees = employeeRepository.findByProjects(project);
        assertEquals(1, employees.size());
        assertEquals(EMPLOYEE_2, employees.get(0));
    }

    @Test
    void findByProjectsIsEmpty() {
        List<Employee> employees = employeeRepository.findByProjectsIsEmpty();
        assertEquals(1, employees.size());
        assertEquals(EMPLOYEE_4, employees.get(0));
    }
}