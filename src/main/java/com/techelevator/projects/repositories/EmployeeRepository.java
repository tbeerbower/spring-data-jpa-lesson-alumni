package com.techelevator.projects.repositories;

import com.techelevator.projects.model.Employee;
import com.techelevator.projects.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByFirstNameIgnoreCaseContainingAndLastNameIgnoreCaseContaining(String firstName, String lastName);
    List<Employee> findByProjects(Project project);
    List<Employee> findByProjectsIsEmpty();
}