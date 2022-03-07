package com.techelevator.projects.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Entity
public class Project {

	@Id
	@SequenceGenerator(name="project_project_id_seq",
		sequenceName="project_project_id_seq",
		allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
		generator="project_project_id_seq")
	@Column(name = "project_id", updatable=false)
	private Long id;
	private String name;
	private LocalDate fromDate;
	private LocalDate toDate;
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinTable(
		name = "project_employee",
		joinColumns = @JoinColumn(name = "project_id"),
		inverseJoinColumns = @JoinColumn(name = "employee_id"))
	private Set<Employee> employees;

	public Project() {}

	public Project(Long id, String name, LocalDate fromDate, LocalDate toDate) {
		this.id = id;
		this.name = name;
		this.fromDate = fromDate;
		this.toDate = toDate;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getFromDate() {
		return fromDate;
	}
	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}
	public LocalDate getToDate() {
		return toDate;
	}
	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Project)) return false;
		Project project = (Project) o;
		return Objects.equals(id, project.getId()) &&
			Objects.equals(name, project.getName()) &&
			Objects.equals(fromDate, project.getFromDate()) &&
			Objects.equals(toDate, project.getToDate());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getName(), getFromDate(), getToDate());
	}
}
