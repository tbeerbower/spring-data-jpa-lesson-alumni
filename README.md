# Database connectivity (JPA) example

For this example, you'll review the JPA entities and repository objects for a CLI application that manages information for employees, departments, and projects. This example is a review sample JPA code that interacts with a database.

Note that the the code for this exercise has been refactored from the following TE student exercise.  Comparing this example to the original exercise should give a good understanding of the major differences from a JDBC DAO approach vs. using a JPA framework and ORM tool.
```
te-curriculum-jan-2023\java\module-2\07_Data_Access_and_DAO\exercise-final
```
### Entities

Note the JPA annotations used to indicate that model objects are JPA entities.

For example, for the Employee entity...
```
@Entity 
public class Employee {
```

And for the identity column...
```
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private int id;
```

And for relationships...
```
    @ManyToOne
	private Department department;
	
	@ManyToMany(mappedBy = "employees", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private Set<Project> projects;	
```

Also, note that each entity should have a `no-arg constructor` and implementations for `equals` and `hashCode`.

### Repositories
Notice that a repository exists for each entity.  In Spring Data JPA a repository interface extends `JpaRepository`.
In many cases, simply extending `JpaRepository` is all you need to do, since the base interface already declares most the methods you would typically use to persist and query an entity.
No implementation of the repository methods should be provided.  The framework will generate the implementation for you!

In some cases, additional methods need to be declared on your repositories for queries not defined on the `JpaRepository` interface.  For example, the `EmployeeRepository`...
```
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByFirstNameIgnoreCaseContainingAndLastNameIgnoreCaseContaining(String firstName, String lastName);
    List<Employee> findByProjects(Project project);
    List<Employee> findByProjectsIsEmpty();
```
For naming these methods, follow the link in the reference section below.

### pom.xml

The project `pom.xml` should contain the following Spring dependencies...
```
        <dependency> 
            <groupId>org.postgresql</groupId> 
            <artifactId>postgresql</artifactId> 
            <version>42.3.3</version> 
        </dependency>
        <dependency> 
            <groupId>org.springframework.boot</groupId> 
            <artifactId>spring-boot-starter-data-jpa</artifactId> 
        </dependency>
```

### Configuration
Note that the Spring configuration is specified in `resources/application.properties`
```
spring.datasource.url=jdbc:postgresql://localhost:5432/employeesjpa
spring.datasource.username=postgres
spring.datasource.password=postgres1
spring.datasource.platform=postgres
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.properties.hibernate.temp.use_jdbc_metadata_defaults=false
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
spring.mvc.pathmatch.matching-strategy=ant-path-matcher
```

### Database
In the configuration above, note the name of the database `employeesjpa`.  The framework will automatically create the database schema (tables, constraints, etc) for you, but the database needs to exist.
You can create the database through `PgAdmin` or some other similar tool.

If you want to load the database with some example data, you can open and run the `resources/data.sql` file in `PgAdmin` or some similar tool.

### References
Repository method naming: 
https://www.javaguides.net/2018/11/spring-data-jpa-query-creation-from-method-names.html