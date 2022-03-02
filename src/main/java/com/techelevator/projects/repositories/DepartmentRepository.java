package com.techelevator.projects.repositories;

import com.techelevator.projects.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DepartmentRepository extends JpaRepository<Department, Long>  {
}
