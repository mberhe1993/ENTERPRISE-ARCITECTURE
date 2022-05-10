package edu.miu.cs.cs544.practice;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
@Data
@Entity
public class Person {
    @Id
    private Long id;
    private String firstName;
    private String lastName;

}
