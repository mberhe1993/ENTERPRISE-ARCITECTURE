package edu.miu.cs.cs544.exercise;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SecondaryTable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude= {"appointments"})
@SecondaryTable(name="Address")
public class Patient {
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	 private String name;
	 @Column(table = "Address")
	 private String street;
	 @Column(table = "Address")
	 private String zip;
	 @Column(table = "Address")
	 private String city;
	 
	 @OneToMany(cascade = CascadeType.ALL,mappedBy = "patient")
	 private List<Appointment> appointments = new ArrayList<Appointment>();
	 
	 public void addAppointment(Appointment appointment){
	        if (appointment!=null){
	            appointment.setPatient(this);
	            appointments.add(appointment);
	        }
	    }

}
