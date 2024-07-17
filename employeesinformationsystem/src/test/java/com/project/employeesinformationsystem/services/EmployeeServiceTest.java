package com.project.employeesinformationsystem.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.project.employeesinformationsystem.entities.Department;
import com.project.employeesinformationsystem.entities.Employee;
import com.project.employeesinformationsystem.entities.Job;
import com.project.employeesinformationsystem.repositories.DepartmentRepository;
import com.project.employeesinformationsystem.repositories.EmployeeRepository;
import com.project.employeesinformationsystem.repositories.JobRepository;

@SpringBootTest
public class EmployeeServiceTest {

    @Mock
    private DepartmentRepository deptRepo;
    @Mock
    private EmployeeRepository empRepo;
    @Mock
    private JobRepository jobRepo;

    @InjectMocks
    private EmployeeService empService;


    @MockBean
    private Department department;
    private Employee employee;
    @MockBean
    private Job job;
   
    @Test
    void testDeleteEmp() {
        job = new Job("Software Engineer");
        jobRepo.save(job);
    
        department = new Department(1, "Software Development", null);
    
        employee = new Employee("Supriya", 250000.00, LocalDate.of(2024, 6, 23), job, department);
        employee.setEmpId(5);
        department.setEmployee(employee);
        deptRepo.save(department);
        empRepo.save(employee);

        when(empRepo.findById(employee.getEmpId())).thenReturn(Optional.of(employee));
        doNothing().when(empRepo).deleteById(employee.getEmpId());

        assertThat(empService.deleteEmp(employee.getEmpId())).isEqualTo(" employee id " + employee.getEmpId() + " deleted successfully.");
        verify(empRepo).deleteById(employee.getEmpId());
        
    }

}
