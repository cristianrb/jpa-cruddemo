package com.cristianrb.springboot.cruddemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cristianrb.springboot.cruddemo.dao.EmployeeDAO;
import com.cristianrb.springboot.cruddemo.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeDAO employeeDAO;
	
	@Autowired
	public EmployeeServiceImpl(@Qualifier("employeeDAOJpaImpl") EmployeeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
	}
	
	@Override
	@Transactional
	public List<Employee> findAll() {
		return this.employeeDAO.findAll();
	}

	@Override
	@Transactional
	public Employee findById(int id) {
		return this.employeeDAO.findById(id);
	}

	@Override
	@Transactional
	public void save(Employee employee) {
		this.employeeDAO.save(employee);
	}

	@Override
	@Transactional
	public void deleteById(int id) {
		this.employeeDAO.deleteById(id);
	}

}
