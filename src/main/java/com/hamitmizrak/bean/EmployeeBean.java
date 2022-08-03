package com.hamitmizrak.bean;

import com.hamitmizrak.data.entity.EmployeeEntity;
import com.hamitmizrak.data.repository.IEmployeeRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.logging.Logger;

@Configuration
public class EmployeeBean {

    @Autowired
    IEmployeeRepository repository;



    @Bean
    public void getAllData(){
        for (int i = 1; i <= 10; i++) {
            EmployeeEntity companyEntity = EmployeeEntity.builder().employeeName("Müşteri adı "+i).employeeSurname("Müşteri soyadı "+i).build();
            repository.save(companyEntity);
        }
    }
}
