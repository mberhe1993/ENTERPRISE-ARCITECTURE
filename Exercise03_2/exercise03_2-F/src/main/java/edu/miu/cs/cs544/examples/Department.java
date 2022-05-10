package edu.miu.cs.cs544.examples;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;

@Entity
public class Department {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="department")
	@OrderColumn
	private List<Employee> employees =new ArrayList<Employee>();

	public Department(String name) {
		super();
		this.name = name;
	}
	
	public Department() {}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	
	public void addemployee(Employee employee) {
		employees.add(employee);
		employee.setDepartment(this);
	}

    public void removeemployee(Employee employee) {
    	employees.remove(employee);
    }
    
	

    @Override
	public String toString() {
		return ", Department Name-->" + name;
	}
	
	

}
