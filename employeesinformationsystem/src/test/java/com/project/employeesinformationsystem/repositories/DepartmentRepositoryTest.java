package com.project.employeesinformationsystem.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.project.employeesinformationsystem.entities.Department;
import com.project.employeesinformationsystem.entities.Employee;
import com.project.employeesinformationsystem.entities.Job;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository deptRepo;
    Department department;

    @Autowired
    private EmployeeRepository empRepo;
    Employee employee;

    @Autowired
    private JobRepository jobRepo;
    Job job;
  

    @BeforeEach
    void setUp() {
        job = new Job("Software Engineer");
        jobRepo.save(job);
    
        department = new Department(1, "Software Development", null);
    
        employee = new Employee("Supriya", 250000.00, LocalDate.of(2024, 6, 23), job, department);
        empRepo.save(employee);
        department.setEmployee(employee);
        deptRepo.save(department);
    }

      @AfterEach
    void tearDown() {
        empRepo.deleteAll(); 
        deptRepo.deleteAll();
        jobRepo.deleteAll(); 
    }
    
    @Test
    void testName() {
        Department foundDepartment=deptRepo.findByDeptName(department.getDeptName());
        assertThat(foundDepartment).isNotNull();
        assertThat(foundDepartment.getDeptName()).isEqualTo(department.getDeptName());     
    } 
}
