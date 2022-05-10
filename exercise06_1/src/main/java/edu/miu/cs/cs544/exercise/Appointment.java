package edu.miu.cs.cs544.exercise;



import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Appointment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String appdate;
	@ManyToOne(cascade = CascadeType.ALL)
	 private Patient patient;
	 @Embedded
	 private Payment payment;
	 @ManyToOne(cascade = CascadeType.ALL)
	 private Doctor doctor;

}
