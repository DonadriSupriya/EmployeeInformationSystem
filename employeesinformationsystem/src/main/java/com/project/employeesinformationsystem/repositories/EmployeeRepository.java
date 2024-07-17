package com.project.employeesinformationsystem.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.employeesinformationsystem.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // 3.List of employees by job
    List<Employee> findAllByJobJobId(int jobId);
 
    // List of employees by dept
    List<Employee> findAllByDepartmentDeptId(int deptId);

    // 5.List of employees where name contains given string
    List<Employee> findAllByEmpNameContains(String empName);

    // 6.List of employees by given salary range
    List<Employee> findAllBySalaryBetween(Double min, Double max);

    

}
