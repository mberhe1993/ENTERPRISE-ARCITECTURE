package edu.miu.cs.cs544.examples;




import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.ToString;


@Entity
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String employeenumber;
	private String name;
	
	@ManyToOne
	@JoinColumn(name="department_Id")
	private Department department;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="office_Id")
	private Office office;
	
	

	public Employee(String employeenumber, String name) {
		super();
		this.employeenumber = employeenumber;
		this.name = name;	
	}
	
	public Employee() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmployeenumber() {
		return employeenumber;
	}

	public void setEmployeenumber(String employeenumber) {
		this.employeenumber = employeenumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
	
	

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", employeenumber=" + employeenumber + ", name=" + name + ", department="
				+ department +  ", office="
						+ office +"]";
	}
	
	
	
	
	

}
