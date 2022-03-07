package com.techelevator.projects.repositories;

import com.techelevator.projects.TestApplication;
import com.techelevator.projects.repositories.DepartmentRepository;
import com.techelevator.projects.repositories.EmployeeRepository;
import com.techelevator.projects.repositories.ProjectRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ContextConfiguration(classes = {TestApplication.class})
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@TestPropertySource(locations="classpath:test.properties")
public class BaseRepositoryTest {
    @Autowired
    protected EmployeeRepository employeeRepository;
    @Autowired
    protected ProjectRepository projectRepository;
    @Autowired
    protected DepartmentRepository departmentRepository;
}
