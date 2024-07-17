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
import com.project.employeesinformationsystem.entities.JobHistory;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class JobHistoryRepositoryTest {

    @Autowired
    private JobHistoryRepository jobHistoryRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

    private Employee employee;
    private Department department;
    private Job job;

    @BeforeEach
    void setUp() {
        job = new Job("Software Engineer");
        jobRepository.save(job);

        department = new Department(1, "Software Developer", null); 

        employee = new Employee("John Doe", 25470.00, LocalDate.of(1990, 5, 15), job, department);
        employeeRepository.save(employee);
        department.setEmployee(employee);
        departmentRepository.save(department);

        JobHistory jobHistory = new JobHistory( LocalDate.of(2019, 6, 23),  LocalDate.of(2024, 6, 23), employee, job);
        jobHistoryRepository.save(jobHistory);
    }

    @AfterEach
    void tearDown() {
        jobHistoryRepository.deleteAll();
        employeeRepository.deleteAll();
        jobRepository.deleteAll();
    }

    @Test
    void testFindAllByEmployeeEmpId() {
        List<JobHistory> jobHistories = jobHistoryRepository.findAllByEmployeeEmpId(employee.getEmpId());
        assertThat(jobHistories).isNotEmpty();
        assertThat(jobHistories.get(0).getEmployee().getEmpId()).isEqualTo(employee.getEmpId());
    }
}
