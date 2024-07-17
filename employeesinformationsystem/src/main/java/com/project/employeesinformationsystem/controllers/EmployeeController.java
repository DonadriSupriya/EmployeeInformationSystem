package com.project.employeesinformationsystem.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.employeesinformationsystem.dto.EmployeeDto;
import com.project.employeesinformationsystem.dto.EmployeePDto;
import com.project.employeesinformationsystem.services.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService empService;

    @PostMapping("/")
    public EmployeeDto addEmployee(@RequestBody EmployeePDto employeePDto) {
        return empService.addEmployee(employeePDto);
    }

    // List of employees
    @GetMapping("/")
    public List<EmployeeDto> getAllEmp() {
        return empService.getAllEmp();
    }

    // 3.List of employees by job
    @GetMapping("/job/{id}")
    public List<EmployeeDto> getEmpByjob(@PathVariable("id") int jobId) {
        return empService.getEmpByjob(jobId);
    }

    // 4.List of employees by dept
    @GetMapping("/department/{id}")
    public List<EmployeeDto> getEmpBydept(@PathVariable("id") int deptId) {
        return empService.getEmpBydept(deptId);
    }

    // 5.List of employees where name contains given string
    @GetMapping("/employeeName/{name}")
    public List<EmployeeDto> getEmpByName(@PathVariable("name") String empName) {
        return empService.getEmpByName(empName);
    }

    // 6.List of employees by given salary range
    @GetMapping("/salaryRange/{min}/{max}")
    public List<EmployeeDto> getEmpBySalary(@PathVariable("min") double min, @PathVariable("max") double max) {
        return empService.getEmpBySalary(min, max);
    }

    // 9.Update salary of employee
    @PutMapping("/{id}/{salary}")
    public List<EmployeeDto> setEmpSalary(@PathVariable("id") int empId, @PathVariable("salary") double salary) {
        return empService.setEmpSalary(empId, salary);
    }

    @DeleteMapping("/{id}")
    public String deleteEmp(@PathVariable("id") int empId) {
        return empService.deleteEmp(empId);
    }

    @GetMapping("/experience")
    public List<EmployeeDto> getExperienceEmployee(@RequestParam("year") int years) {
        return empService.getExperienceEmployee(years);
    }
}
