package edu.miu.cs.cs544.examples;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@DiscriminatorValue("dvd")
public class DVD extends Product {
	private String genre;

}
