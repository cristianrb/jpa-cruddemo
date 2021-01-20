package com.cristianrb.springboot.cruddemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cristianrb.springboot.cruddemo.entity.Employee;
import com.cristianrb.springboot.cruddemo.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

	private EmployeeService employeeService;

	// Inject employee dao (Constructor injection)
	@Autowired
	public EmployeeRestController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	// Expose "/employees" and return list of employees
	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
		return this.employeeService.findAll();
	}

	// Add mapping for GET  "/employees/{employeeId}" 
	@GetMapping("/employees/{employeeId}")
	public Employee getEmployeeById(@PathVariable int employeeId) {
		Employee employee = this.employeeService.findById(employeeId);
		if (employee == null) {
			throw new RuntimeException("Employee id not found - " + employeeId);
		}
		return employee;
	}

	// Add mapping for POST /employees - add new employee
	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee employee) {
		employee.setId(0);
		this.employeeService.save(employee);
		return employee;
	}

	// Add mapping for PUT /employees - update employee
	@PutMapping("/employees")
	public Employee updateEmployee(@RequestBody Employee employee) {
		this.employeeService.save(employee);
		return employee;
	}

	// Add mapping for DELETE /employees/{employeeId} - delete employee
	@DeleteMapping("/employees/{employeeId}")
	public String getAllEmployees(@PathVariable int employeeId) {
		Employee employee = this.employeeService.findById(employeeId);
		if (employee == null) {
			throw new RuntimeException("Employee id not found - " + employeeId);
		}
		this.employeeService.deleteById(employeeId);
		return "Deleted employee with id - " + employeeId;
	}
}
