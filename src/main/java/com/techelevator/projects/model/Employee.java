package com.techelevator.projects.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Entity
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long employeeId;
	@ManyToOne
	private Department department;
	private String firstName;
	private String lastName;
	private LocalDate birthDate;
	private LocalDate hireDate;
	@ManyToMany(mappedBy = "employees", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private Set<Project> projects;

	public Employee() {}

	public Employee(Long employeeId, Department department, String firstName, String lastName,
					LocalDate birthDate, LocalDate hireDate) {
		this.employeeId = employeeId;
		this.department = department;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.hireDate = hireDate;
	}

	public Long getId() {
		return employeeId;
	}
	public void setId(Long employeeId) {
		this.employeeId = employeeId;
	}
	public Department getDepartment() {
		return department;
	}
	// department
	public void setDepartment(Department department) {
		this.department = department;
	}
	public String getFirstName() {
		return firstName;
	}
    // -----
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public LocalDate getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
	public LocalDate getHireDate() {
		return hireDate;
	}
	public void setHireDate(LocalDate hireDate) {
		this.hireDate = hireDate;
	}
    // projects
	public Set<Project> getProjects() {
		return projects;
	}
	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}
	// -----

	@Override
	public String toString() {
		return lastName + ", " + firstName;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if(!(o instanceof Employee)) return false;
		Employee employee = (Employee) o;
		return Objects.equals(employeeId, employee.getId()) &&
			Objects.equals(department, employee.getDepartment()) &&
			Objects.equals(firstName, employee.getFirstName()) &&
			Objects.equals(lastName, employee.getLastName()) &&
			Objects.equals(birthDate, employee.getBirthDate()) &&
			Objects.equals(hireDate, employee.getHireDate());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getDepartment(), getFirstName(), getLastName(), getBirthDate(), getHireDate());
	}
}
