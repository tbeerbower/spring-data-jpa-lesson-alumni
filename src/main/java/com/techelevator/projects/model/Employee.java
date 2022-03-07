package com.techelevator.projects.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Entity
public class Employee {
	@Id
	@SequenceGenerator(name="employee_employee_id_seq",
		sequenceName="employee_employee_id_seq",
		allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
		generator="employee_employee_id_seq")
	@Column(name = "employee_id", updatable=false)
	private Long employeeId;
	private Long departmentId;
	private String firstName;
	private String lastName;
	private LocalDate birthDate;
	private LocalDate hireDate;
	@ManyToMany(mappedBy = "employees", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	private Set<Project> projects;

	public Employee() {}

	public Employee(Long employeeId, Long departmentId, String firstName, String lastName,
					LocalDate birthDate, LocalDate hireDate) {
		this.employeeId = employeeId;
		this.departmentId = departmentId;
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
	public long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	public String getFirstName() {
		return firstName;
	}
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

	public Set<Project> getProjects() {
		return projects;
	}

	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}

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
			Objects.equals(departmentId, employee.getDepartmentId()) &&
			Objects.equals(firstName, employee.getFirstName()) &&
			Objects.equals(lastName, employee.getLastName()) &&
			Objects.equals(birthDate, employee.getBirthDate()) &&
			Objects.equals(hireDate, employee.getHireDate());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getDepartmentId(), getFirstName(), getLastName(), getBirthDate(), getHireDate());
	}
}
