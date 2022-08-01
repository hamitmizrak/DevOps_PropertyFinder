package com.hamitmizrak.data.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data


@Entity
@Table(name="employee")
public class EmployeeEntity extends BaseEntity {

    @Column(name="employee_name")
    private String employeeName;

    @Column(name="employee_surname")
    private String employeeSurname;

}
