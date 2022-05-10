package edu.miu.cs.cs544.exercises.a;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String firstname;
	private String lastname;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="employee")
	private Set<Laptop> laptops =new HashSet<Laptop>();
	
	void addLaptop(Laptop laptop) {
		laptops.add(laptop);
		laptop.setEmployee(this);
	}

	
	
}
