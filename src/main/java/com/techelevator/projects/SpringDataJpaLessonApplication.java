package com.techelevator.projects;

import com.techelevator.projects.model.Department;
import com.techelevator.projects.model.Employee;
import com.techelevator.projects.model.Project;
import com.techelevator.projects.repositories.DepartmentRepository;
import com.techelevator.projects.repositories.EmployeeRepository;
import com.techelevator.projects.repositories.ProjectRepository;
import com.techelevator.projects.view.Menu;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class SpringDataJpaLessonApplication implements CommandLineRunner {
	private static final String MAIN_MENU_OPTION_EMPLOYEES = "Employees";
	private static final String MAIN_MENU_OPTION_DEPARTMENTS = "Departments";
	private static final String MAIN_MENU_OPTION_PROJECTS = "Projects";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = new String[] { MAIN_MENU_OPTION_DEPARTMENTS,
		MAIN_MENU_OPTION_EMPLOYEES,
		MAIN_MENU_OPTION_PROJECTS,
		MAIN_MENU_OPTION_EXIT };

	private static final String MENU_OPTION_RETURN_TO_MAIN = "Return to main menu";

	private static final String DEPT_MENU_OPTION_ALL_DEPARTMENTS = "Show all departments";
	private static final String DEPT_MENU_OPTION_UPDATE_NAME = "Update department name";
	private static final String[] DEPARTMENT_MENU_OPTIONS = new String[] { DEPT_MENU_OPTION_ALL_DEPARTMENTS,
		DEPT_MENU_OPTION_UPDATE_NAME,
		MENU_OPTION_RETURN_TO_MAIN};

	private static final String EMPL_MENU_OPTION_ALL_EMPLOYEES = "Show all employees";
	private static final String EMPL_MENU_OPTION_SEARCH_BY_NAME = "Employee search by name";
	private static final String EMPL_MENU_OPTION_EMPLOYEES_NO_PROJECTS = "Show employees without projects";
	private static final String[] EMPL_MENU_OPTIONS = new String[] { EMPL_MENU_OPTION_ALL_EMPLOYEES,
		EMPL_MENU_OPTION_SEARCH_BY_NAME,
		EMPL_MENU_OPTION_EMPLOYEES_NO_PROJECTS,
		MENU_OPTION_RETURN_TO_MAIN};

	private static final String PROJ_MENU_OPTION_ACTIVE_PROJECTS = "Show all projects";
	private static final String PROJ_MENU_OPTION_ADD_PROJECT = "Add new project";
	private static final String PROJ_MENU_OPTION_PROJECT_EMPLOYEES = "Show project employees";
	private static final String PROJ_MENU_OPTION_ASSIGN_EMPLOYEE_TO_PROJECT = "Assign an employee to a project";
	private static final String PROJ_MENU_OPTION_REMOVE_EMPLOYEE_FROM_PROJECT = "Remove employee from project";
	private static final String PROJ_MENU_OPTION_DELETE_PROJECT = "Delete project";
	private static final String[] PROJ_MENU_OPTIONS = new String[] { PROJ_MENU_OPTION_ACTIVE_PROJECTS,
		PROJ_MENU_OPTION_ADD_PROJECT,
		PROJ_MENU_OPTION_PROJECT_EMPLOYEES,
		PROJ_MENU_OPTION_ASSIGN_EMPLOYEE_TO_PROJECT,
		PROJ_MENU_OPTION_REMOVE_EMPLOYEE_FROM_PROJECT,
		PROJ_MENU_OPTION_DELETE_PROJECT,
		MENU_OPTION_RETURN_TO_MAIN };


	private final Menu menu;
	private final EmployeeRepository employeeRepository;
	private final DepartmentRepository departmentRepository;
	private final ProjectRepository projectRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpaLessonApplication.class, args);
	}

	public SpringDataJpaLessonApplication(EmployeeRepository employeeRepository,
										  DepartmentRepository departmentRepository,
										  ProjectRepository projectRepository) {
		this.employeeRepository = employeeRepository;
		this.departmentRepository = departmentRepository;
		this.projectRepository = projectRepository;
		this.menu = new Menu(System.in, System.out);
	}

	@Override
	public void run(String... args) throws Exception {

		displayApplicationBanner();
		boolean running = true;
		while(running) {
			printHeading("Main Menu");
			String choice = (String)menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			if(choice.equals(MAIN_MENU_OPTION_DEPARTMENTS)) {
				handleDepartments();
			} else if(choice.equals(MAIN_MENU_OPTION_EMPLOYEES)) {
				handleEmployees();
			} else if(choice.equals(MAIN_MENU_OPTION_PROJECTS)) {
				handleProjects();
			} else if(choice.equals(MAIN_MENU_OPTION_EXIT)) {
				running = false;
			}
		}
	}

	private void handleDepartments() {
		printHeading("Departments");
		String choice = (String)menu.getChoiceFromOptions(DEPARTMENT_MENU_OPTIONS);
		if(choice.equals(DEPT_MENU_OPTION_ALL_DEPARTMENTS)) {
			handleListAllDepartments();
		} else if(choice.equals(DEPT_MENU_OPTION_UPDATE_NAME)) {
			handleUpdateDepartmentName();
		}
	}

	private void handleUpdateDepartmentName() {
		printHeading("Update Department Name");
		List<Department> allDepartments = departmentRepository.findAll();
		if(allDepartments.size() > 0) {
			System.out.println("\n*** Choose a Department ***");
			Department selectedDepartment = (Department)menu.getChoiceFromOptions(allDepartments.toArray());
			String newDepartmentName = getUserInput("Enter new Department name");
			selectedDepartment.setName(newDepartmentName);
			departmentRepository.save(selectedDepartment);
		} else {
			System.out.println("\n*** No results ***");
		}
	}

	private void handleListAllDepartments() {
		printHeading("All Departments");
		List<Department> allDepartments = departmentRepository.findAll();
		listDepartments(allDepartments);
	}


	private void listDepartments(List<Department> departments) {
		System.out.println();
		if(departments.size() > 0) {
			for(Department dept : departments) {
				System.out.println(dept);
			}
		} else {
			System.out.println("\n*** No results ***");
		}
	}

	private void handleEmployees() {
		printHeading("Employees");
		String choice = (String)menu.getChoiceFromOptions(EMPL_MENU_OPTIONS);
		if(choice.equals(EMPL_MENU_OPTION_ALL_EMPLOYEES)) {
			handleListAllEmployees();
		} else if(choice.equals(EMPL_MENU_OPTION_SEARCH_BY_NAME)) {
			handleEmployeeSearch();
		} else if(choice.equals(EMPL_MENU_OPTION_EMPLOYEES_NO_PROJECTS)) {
			handleUnassignedEmployeeSearch();
		}
	}

	private void handleListAllEmployees() {
		printHeading("All Employees");
		List<Employee> allEmployees = employeeRepository.findAll();
		listEmployees(allEmployees);
	}

	private void handleEmployeeSearch() {
		printHeading("Employee Search");
		String firstNameSearch = getUserInput("Enter first name to search for");
		String lastNameSearch = getUserInput("Enter last name to search for");
		List<Employee> employees =
			employeeRepository.findByFirstNameIgnoreCaseContainingAndLastNameIgnoreCaseContaining(firstNameSearch, lastNameSearch);
		listEmployees(employees);
	}

	private void handleUnassignedEmployeeSearch() {
		printHeading("Unassigned Employees");
		List<Employee> employees = employeeRepository.findByProjectsIsEmpty();
		listEmployees(employees);
	}

	private void listEmployees(List<Employee> employees) {
		System.out.println();
		if(employees.size() > 0) {
			for(Employee employee : employees) {
				System.out.println(employee);
			}
		} else {
			System.out.println("\n*** No results ***");
		}
	}



	private void handleProjects() {
		printHeading("Projects");
		String choice = (String)menu.getChoiceFromOptions(PROJ_MENU_OPTIONS);
		if(choice.equals(PROJ_MENU_OPTION_ACTIVE_PROJECTS)) {
			handleListActiveProjects();
		} else if(choice.equals(PROJ_MENU_OPTION_ADD_PROJECT)) {
			handleAddProject();
		} else if(choice.equals(PROJ_MENU_OPTION_PROJECT_EMPLOYEES)) {
			handleProjectEmployeeList();
		} else if(choice.equals(PROJ_MENU_OPTION_ASSIGN_EMPLOYEE_TO_PROJECT)) {
			handleEmployeeProjectAssignment();
		}  else if(choice.equals(PROJ_MENU_OPTION_REMOVE_EMPLOYEE_FROM_PROJECT)) {
			handleEmployeeProjectRemoval();
		}  else if(choice.equals(PROJ_MENU_OPTION_DELETE_PROJECT)) {
			handleDeleteProject();
		}
	}

	private void handleListActiveProjects() {
		printHeading("Active Projects");
		List<Project> projects = projectRepository.findAll();
		listProjects(projects);
	}

	@Transactional
	private void handleAddProject() {
		printHeading("Add New Project");
		String newProjectName = getUserInput("Enter new Project name");
		String startDate = getUserInput("Enter start date (YYYY-MM-DD)");
		String endDate = getUserInput("Enter end date (YYYY-MM-DD)");
		Project newProject = new Project();
		newProject.setName(newProjectName);
		newProject.setFromDate(LocalDate.parse(startDate));
		newProject.setToDate(LocalDate.parse(endDate));
		newProject = projectRepository.save(newProject);
		System.out.println("\n*** "+newProject.getName()+" created ***");
	}

	@Transactional
	private void handleEmployeeProjectRemoval() {
		printHeading("Remove Employee From Project");

		Project selectedProject = getProjectSelectionFromUser();

		System.out.println("Choose an employee to remove:");
		List<Employee> projectEmployees = employeeRepository.findByProjects(selectedProject);
		if(projectEmployees.size() > 0) {
			Employee selectedEmployee = (Employee)menu.getChoiceFromOptions(projectEmployees.toArray());
			selectedProject.getEmployees().remove(selectedEmployee);
			projectRepository.save(selectedProject);
			System.out.println("\n*** "+selectedEmployee+" removed from "+selectedProject+" ***");
		} else {
			System.out.println("\n*** No results ***");
		}
	}

	@Transactional
	private void handleEmployeeProjectAssignment() {
		printHeading("Assign Employee To Project");

		Project selectedProject = getProjectSelectionFromUser();

		System.out.println("Choose an employee to add:");
		List<Employee> allEmployees = employeeRepository.findAll();
		Employee selectedEmployee = (Employee)menu.getChoiceFromOptions(allEmployees.toArray());
		selectedProject.getEmployees().add(selectedEmployee);
		projectRepository.save(selectedProject);
		System.out.println("\n*** "+selectedEmployee+" added to "+selectedProject+" ***");
	}

	@Transactional
	private void handleDeleteProject() {
		printHeading("Delete Project");
		Project selectedProject = getProjectSelectionFromUser();

		selectedProject.getEmployees().clear();
		projectRepository.save(selectedProject);
		projectRepository.deleteById(selectedProject.getId());
		System.out.println("\n*** " + selectedProject.getName() + " deleted ***");
	}

	private void handleProjectEmployeeList() {
		Project selectedProject = getProjectSelectionFromUser();
		List<Employee> projectEmployees = employeeRepository.findByProjects(selectedProject);
		listEmployees(projectEmployees);
	}

	private Project getProjectSelectionFromUser() {
		System.out.println("Choose a project:");
		List<Project> allProjects = projectRepository.findAll();
		return (Project)menu.getChoiceFromOptions(allProjects.toArray());
	}

	private void listProjects(List<Project> projects) {
		System.out.println();
		if(projects.size() > 0) {
			for(Project proj : projects) {
				System.out.println(proj);
			}
		} else {
			System.out.println("\n*** No results ***");
		}
	}

	private void printHeading(String headingText) {
		System.out.println("\n"+headingText);
		for(int i = 0; i < headingText.length(); i++) {
			System.out.print("-");
		}
		System.out.println();
	}

	private String getUserInput(String prompt) {
		System.out.print(prompt + " >>> ");
		return new Scanner(System.in).nextLine();
	}

	private void displayApplicationBanner() {
		System.out.println(" ______                 _                         _____           _           _     _____  ____");
		System.out.println("|  ____|               | |                       |  __ \\         (_)         | |   |  __ \\|  _ \\");
		System.out.println("| |__   _ __ ___  _ __ | | ___  _   _  ___  ___  | |__) | __ ___  _  ___  ___| |_  | |  | | |_) |");
		System.out.println("|  __| | '_ ` _ \\| '_ \\| |/ _ \\| | | |/ _ \\/ _ \\ |  ___/ '__/ _ \\| |/ _ \\/ __| __| | |  | |  _ <");
		System.out.println("| |____| | | | | | |_) | | (_) | |_| |  __/  __/ | |   | | | (_) | |  __/ (__| |_  | |__| | |_) |");
		System.out.println("|______|_| |_| |_| .__/|_|\\___/ \\__, |\\___|\\___| |_|   |_|  \\___/| |\\___|\\___|\\__| |_____/|____/");
		System.out.println("                 | |             __/ |                          _/ |");
		System.out.println("                 |_|            |___/                          |__/");
		System.out.println();
	}
}
