package com.hamitmizrak.ui.rest;

import com.hamitmizrak.business.dto.EmployeeDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface IEmployeeRest {

    //saveAllData
    public  List<EmployeeDto>    saveAllDataEmployee();

    //SAVE
    EmployeeDto createEmployee(EmployeeDto employeeDto);

    //LIST
    List<EmployeeDto> getAllEmployees();

    //FIND
    ResponseEntity<EmployeeDto> getEmployeeById(Long id);

    //DELETE
    ResponseEntity<Map<String,Boolean>> deleteEmployee(Long id);


    //UPDATE
    ResponseEntity<EmployeeDto> updateEmployeeById(Long id,EmployeeDto employeeDto);

}
