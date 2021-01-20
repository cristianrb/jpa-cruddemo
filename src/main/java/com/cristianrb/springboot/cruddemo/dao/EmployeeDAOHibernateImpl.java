package com.cristianrb.springboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cristianrb.springboot.cruddemo.entity.Employee;

@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO {

	// Define field for entitymanager
	private EntityManager entityManager;
	
	// set up constructor injection
	@Autowired
	public EmployeeDAOHibernateImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public List<Employee> findAll() {
		// Get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		// Create a query
		Query<Employee> query = currentSession.createQuery("from Employee", Employee.class);
		// Execute a query and get result list
		List<Employee> employees = query.getResultList();
		// Return the results
		return employees;
	}

	@Override
	public Employee findById(int id) {
		// Get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		// Get the employee
		Employee employee = currentSession.get(Employee.class, id);
		// Return the employee
		return employee;
	}

	@Override
	public void save(Employee employee) {
		// Get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		// Save the employee
		currentSession.saveOrUpdate(employee);
	}

	@Override
	public void deleteById(int id) {
		// Get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		// Delete object with primary key
		Query query = currentSession.createQuery("delete from Employee where id =: employeeId");
		query.setParameter("employeeId", id);
		query.executeUpdate();
	}

}
