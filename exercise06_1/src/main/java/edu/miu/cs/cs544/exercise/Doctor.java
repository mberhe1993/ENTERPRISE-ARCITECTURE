package edu.miu.cs.cs544.exercise;

import java.util.ArrayList;
import java.util.List;

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
@ToString(exclude={"appointments"})
public class Doctor {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private int id;
	 private String doctortype;
	 private String firstname;
	 private String lastname;
	 
	 @OneToMany(mappedBy = "doctor",cascade = CascadeType.ALL)
	 private List<Appointment> appointments = new ArrayList<Appointment>();
	 
	 public void addAppointment(Appointment appointment){
	        if (appointment!=null){
	            appointment.setDoctor(this);
	            appointments.add(appointment);
	        }
	    }
}
