package com.project.employeesinformationsystem.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.project.employeesinformationsystem.dto.DepartmentGetDto;
import com.project.employeesinformationsystem.entities.Department;
import com.project.employeesinformationsystem.entities.Employee;
import com.project.employeesinformationsystem.entities.Job;
import com.project.employeesinformationsystem.repositories.DepartmentRepository;
import com.project.employeesinformationsystem.repositories.EmployeeRepository;
import com.project.employeesinformationsystem.repositories.JobRepository;

@SpringBootTest
public class DepartmentServiceTest {

    @Mock
    private DepartmentRepository deptRepo;
    @Mock
    private EmployeeRepository empRepo;
    @Mock
    private JobRepository jobRepo;

    @InjectMocks
    private DepartmentService deptService;
  
    private Department department;
    @MockBean
    private Employee employee;
    @MockBean
    private Job job;

    @Test
    void testGetAllDept() {
        job = new Job("Software Engineer");
        jobRepo.save(job);
    
        department = new Department(1, "Software Development", null);
    
        employee = new Employee("Supriya", 250000.00, LocalDate.of(2024, 6, 23), job, department);
        
        employee.setEmpId(5);
        empRepo.save(employee);
        department.setEmployee(employee);
        deptRepo.save(department);
        
        DepartmentGetDto dto=new DepartmentGetDto();
        dto.setDeptId(department.getDeptId());
        dto.setDeptName(department.getDeptName());
        dto.setEmpId(department.getEmployee().getEmpId());
        when(deptRepo.save(department)).thenReturn(department);
        assertThat(deptService.getAllDept()).isNotNull();
    }
}
