package com.cristianrb.springboot.cruddemo.dao;

import java.util.List;

import com.cristianrb.springboot.cruddemo.entity.Employee;

public interface EmployeeDAO {

	public List<Employee> findAll();
	
	public Employee findById(int id);
	
	public void save(Employee employee);
	
	public void deleteById(int id);
	
}
