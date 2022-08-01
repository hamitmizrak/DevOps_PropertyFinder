package com.hamitmizrak.business.services.impl;

import com.hamitmizrak.business.dto.EmployeeDto;
import com.hamitmizrak.business.services.IEmployeeServices;
import com.hamitmizrak.data.entity.EmployeeEntity;
import com.hamitmizrak.data.repository.IEmployeeRepository;
import com.hamitmizrak.exception.ResourceNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@Log4j2
public class EmployeeServicesImpl implements IEmployeeServices {

    @Autowired
    IEmployeeRepository repository;

    @Autowired
    ModelMapper modelMapper;

    //model mapper
    @Override
    public EmployeeDto entityToDto(EmployeeEntity employeeEntity) {
        EmployeeDto dto=modelMapper.map(employeeEntity,EmployeeDto.class);
        return dto;
    }

    @Override
    public EmployeeEntity dtoToEntity(EmployeeDto employeeDto) {
        EmployeeEntity entity=modelMapper.map(employeeDto,EmployeeEntity.class);
        return entity;
    }


    //SAVE ALL DATA
    //http://locallhost:8080/save/all/employees
    @Override
    @PostMapping("/save/all/employees")
    public List<EmployeeDto> saveAllDataEmployee() {
        List<EmployeeDto> list = new ArrayList<>();
        for (int i = 1; i <=10; i++) {
            EmployeeEntity entity=    EmployeeEntity.builder(). employeeName("Adı "+i).employeeSurname("Soyadı "+i).build();
            repository.save(entity);
            list.add(entityToDto(entity));
        }
        return list;
    }


    //SAVE
    //http://locallhost:8080/save/employees
    @Override
    @PostMapping("/save/employees")
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        EmployeeEntity entity=    dtoToEntity(employeeDto);
        repository.save(entity);
        return employeeDto;
    }

    //LIST
    //http://localhost:8080/list/employees
    @GetMapping("/list/employees")
    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<EmployeeDto> list = new ArrayList<>();
        Iterable<EmployeeEntity> listem = repository.findAll();
        for (EmployeeEntity entity : listem) {
            EmployeeDto adminDto = entityToDto(entity);
            list.add(adminDto);
        }
        return list;
    }


    //FINDBYID
    //http://locallhost:8080/find/employees
    //http://locallhost:8080/find/employees/1
    @Override
    @GetMapping({"/find/employees","/find/employees/{id}"})
    public ResponseEntity<EmployeeDto> getEmployeeById( @PathVariable(name="id",required = false) Long id) {
      EmployeeEntity entity=  repository.findById(id).orElseThrow( ()->new ResourceNotFoundException(id+" id YOKTUR"));
      EmployeeDto dto=entityToDto(entity);
        return ResponseEntity.ok(dto);
    }

    //DELETE
    //http://locallhost:8080/delete/employees
    //http://locallhost:8080/delete/employees/1
    @Override
    @DeleteMapping({"/delete/employees","/delete/employees/{id}"})
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable(name="id",required = false) Long id) {
        EmployeeEntity entity=  repository.findById(id).orElseThrow( ()->new ResourceNotFoundException(id+" id YOKTUR"));
        repository.delete(entity);
        Map<String,Boolean> response=new HashMap<>();
        response.put("SİLİNDİ",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }


    //GÜNCELLEMEK
    //http://locallhost:8080/update/employees
    //http://locallhost:8080/update/employees/1
    @Override
    @PutMapping({"/update/employees","/update/employees/{id}"})
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable(name="id",required = false) Long id, EmployeeDto employeeDto) {
        EmployeeEntity entityFind=  repository.findById(id).orElseThrow( ()->new ResourceNotFoundException(id+" id YOKTUR"));
        EmployeeEntity entity=dtoToEntity(employeeDto);
        entityFind.setEmployeeName(entity.getEmployeeName());
        entityFind.setEmployeeSurname(entity.getEmployeeSurname());
        EmployeeEntity saveEntity=repository.save(entityFind);
        EmployeeDto dto=entityToDto(saveEntity);
        return ResponseEntity.ok(dto);
    }
}
