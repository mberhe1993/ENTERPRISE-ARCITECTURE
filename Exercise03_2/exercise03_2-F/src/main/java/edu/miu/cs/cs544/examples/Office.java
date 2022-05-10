package edu.miu.cs.cs544.examples;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Getter
@Setter

public class Office {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String roomnumber;
	private String building;
	
	@OneToMany(cascade = CascadeType.PERSIST)
	@JoinColumn(name="office_id")
	private List<Employee> employees=new ArrayList<Employee>();
	
	@Override
	public String toString() {
		return ", Office number-->" + roomnumber;
	}
	
	
}
