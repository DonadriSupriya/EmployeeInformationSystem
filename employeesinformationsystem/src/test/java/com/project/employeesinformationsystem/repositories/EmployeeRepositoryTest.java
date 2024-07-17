package com.project.employeesinformationsystem.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;

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
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository empRepo;
    @Autowired
    private JobRepository jobRepo;
    @Autowired
    private DepartmentRepository deptRepo;

    private Department department;
    private Employee employee;
    private Job job;


    @BeforeEach
    void setUp() {
        job = new Job("Software Engineer");
        jobRepo.save(job);

        department = new Department(1, "Software Development", null);
        deptRepo.save(department);

        employee = new Employee("Supriya", 30000.00, LocalDate.of(2024, 6, 23), job, department);
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
    void testFindAllByJobJobId() {
        List<Employee> foundEmployees = empRepo.findAllByJobJobId(job.getJobId());
        assertThat(foundEmployees).isNotEmpty();
        assertThat(foundEmployees.get(0).getJob().getJobId()).isEqualTo(employee.getJob().getJobId());
    }

    @Test
    void testFindAllByDepartmentDeptId(){
    List<Employee> foundEmployees=empRepo.findAllByDepartmentDeptId(department.getDeptId());
    assertThat(foundEmployees).isNotEmpty();
    assertThat(foundEmployees.get(0).getDepartment().getDeptId()).isEqualTo(employee.getDepartment().getDeptId());
    }

    @Test
    void testFindAllByEmpNameContains() {
    List<Employee> founEmployees=empRepo.findAllByEmpNameContains(employee.getEmpName());
    assertThat(founEmployees).isNotEmpty();
    assertThat(founEmployees.get(0).getEmpName()).isEqualTo(employee.getEmpName());
    }

    @Test
    void testFindAllBySalaryBetween() {
        List<Employee> foundEmployess=empRepo.findAllBySalaryBetween(30000.00,50000.00);
        assertThat(foundEmployess).isNotEmpty();
        Employee firEmployee=foundEmployess.get(0);
        assertThat(firEmployee.getSalary()).isEqualTo(employee.getSalary());
    }
}
