package edu.miu.cs.cs544.RESTfulWebService.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name="adventureworks.department")
//@NamedQuery(name = "department.findByGroupName",
//        query = "select d from Department d where d.groupName = 'Manufacturing' ")
public class Department {

    @Id
    @Column(name="DepartmentID")
    private int departmentId;
    @Column(name="Name")
    private  String name;
    @Column(name="GroupName")
    private  String groupName;
    @Column(name="ModifiedDate")
    private LocalDate modifiedDate;
}
