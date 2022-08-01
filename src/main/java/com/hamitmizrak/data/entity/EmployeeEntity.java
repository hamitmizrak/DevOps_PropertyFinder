package com.hamitmizrak.data.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder


@Entity
@Table(name="employee")
public class EmployeeEntity extends BaseEntity {

    @Column(name="employee_name")
    private String employeeName;

    @Column(name="employee_surname")
    private String employeeSurname;

}
