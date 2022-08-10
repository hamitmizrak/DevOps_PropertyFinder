package com.hamitmizrak;

import com.hamitmizrak.data.entity.EmployeeEntity;
import com.hamitmizrak.data.repository.IEmployeeRepository;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.LIST;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class DevOpsPropertyFinderApplicationTests implements ITestData {

    private static String data =" number: 44";

    @Autowired
  IEmployeeRepository repository;

    @BeforeAll
    static void getBeforeAllMethod(){
        data=" number: 55";
        System.out.println("Herşeyden Önce ben çalışırım "+data);
    }


    @BeforeEach
    void getBeforeEach(){
        System.out.println("Testten  hemen Önce çalışırım");
    }

    @Override
    @Test
    public void testCreate() {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setEmployeeName("müşteri adı 33");
        employeeEntity.setEmployeeSurname("müşteri soyadı 33");
        repository.save(employeeEntity);
        assertNotNull(repository.findById(1L).get());
    }

    @Override
    @Test
    public void testFindById() {
        EmployeeEntity employeeEntity = repository.findById(1L).get();
        assertEquals("müşteri adı 33", employeeEntity.getEmployeeName());
    }

    @Override
    @Test
    public void testList() {
        List<EmployeeEntity> entityList = repository.findAll();
        assertThat(entityList).size().isGreaterThan(0);
    }

    @Override
    @Test
    public void testUpdate() {
        EmployeeEntity entity = repository.findById(1L).get();
        entity.setEmployeeName("müşteri adı 44");
        repository.save(entity);

        assertNotEquals("müşteri adı 33", repository.findById(1L).get().getEmployeeName());

    }

    @Override
    @Test
    public void testDelete() {
        repository.deleteById(1L);
        assertThat(repository.existsById(1L)).isFalse();
    }

    @Test
    public void getFail(){
        Assertions.fail("Bilerek hata gönderdim");
    }

    @Test
    @Disabled("disabled")
    public void getDisable(){
        Assertions.fail("Bilerek Testi kapattım");
    }

    @AfterEach
    void getAfterEach(){
        System.out.println("Testten  hemen Sonra çalışırım");
    }

    @AfterAll
    static void getAfterAllMethod(){
        System.out.println("Bütün testlerden en sonra çalışacak");
    }



}
