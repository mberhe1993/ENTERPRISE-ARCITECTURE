package edu.miu.cs.cs544.RESTfulWebService.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name="adventureworks.culture")
public class Culture {
    @Id
    @Column(name="CultureID")
    private String cultureID;
    @Column(name="Name")
    private  String name;
    @Column(name="ModifiedDate")
    private LocalDate modifiedDate;
}
