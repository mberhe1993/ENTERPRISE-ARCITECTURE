package edu.miu.cs.cs544.exercises.b;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Passenger {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="Passenger_id")
	@OrderBy("fromplace DESC")
	private List<Flight> flights =new ArrayList<Flight>();
	
	void addFlight(Flight flight) {
		flights.add(flight);
		
	}

}
