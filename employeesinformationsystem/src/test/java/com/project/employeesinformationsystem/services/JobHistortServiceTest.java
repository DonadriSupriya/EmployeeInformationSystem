package com.project.employeesinformationsystem.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.project.employeesinformationsystem.dto.JobHistoryDto;
import com.project.employeesinformationsystem.entities.Department;
import com.project.employeesinformationsystem.entities.Employee;
import com.project.employeesinformationsystem.entities.Job;
import com.project.employeesinformationsystem.entities.JobHistory;
import com.project.employeesinformationsystem.repositories.DepartmentRepository;
import com.project.employeesinformationsystem.repositories.EmployeeRepository;
import com.project.employeesinformationsystem.repositories.JobHistoryRepository;
import com.project.employeesinformationsystem.repositories.JobRepository;

@SpringBootTest
public class JobHistortServiceTest {
    @Mock
    private DepartmentRepository deptRepo;
    @Mock
    private EmployeeRepository empRepo;
    @Mock
    private JobRepository jobRepo;
    @Mock
    private JobHistoryRepository jobHisRepo;

    @InjectMocks
    private JobHistortService jobHisService;

    private JobHistory jobHistory;
    @MockBean
    private Department department;
    @MockBean
    private Employee employee;
    @MockBean
    private Job job;
   
    @Test
    void testUpdateEnddate() {
        job = new Job("Software Engineer");
        jobRepo.save(job);

        department = new Department(1, "Software Developer", null); 

        employee = new Employee("John Doe", 25470.00, LocalDate.of(1990, 5, 15), job, department);
        empRepo.save(employee);
        department.setEmployee(employee);
        deptRepo.save(department);

        jobHistory = new JobHistory( LocalDate.of(2019, 6, 23),  LocalDate.of(2024, 6, 23), employee, job);
        jobHistory.setJobHistoryId(1);
        jobHisRepo.save(jobHistory);

        JobHistoryDto dto=new JobHistoryDto();
        dto.setJobHistoryId(jobHistory.getJobHistoryId());
        dto.setStartDate(jobHistory.getStartDate());
        dto.setEndDate(jobHistory.getEndDate());
        dto.setEmpId(jobHistory.getEmployee().getEmpId());
        dto.setJobId(jobHistory.getJob().getJobId());

        when(jobHisRepo.findById(1)).thenReturn(Optional.of(jobHistory));
        when(jobHisRepo.save(jobHistory)).thenReturn(jobHistory);
        assertThat(jobHisService.updateEnddate(jobHistory.getJobHistoryId(),jobHistory.getEndDate())).isEqualTo(dto);

    }
}
