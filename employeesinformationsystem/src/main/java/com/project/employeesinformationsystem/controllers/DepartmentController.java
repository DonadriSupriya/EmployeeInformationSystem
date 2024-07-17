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
import org.springframework.web.bind.annotation.RestController;

import com.project.employeesinformationsystem.dto.DepartmentGetDto;
import com.project.employeesinformationsystem.entities.Department;
import com.project.employeesinformationsystem.services.DepartmentService;

@RestController
@RequestMapping("/department")
public class DepartmentController {
   
    @Autowired
    private DepartmentService deptService;

    @PostMapping("/")
    public DepartmentGetDto addDepartment(@RequestBody Department department) {
        return deptService.addDepartment(department);
    }

    // 2.List of departments
    @GetMapping("/")
    public List<DepartmentGetDto> getAllDept() {
        return deptService.getAllDept();
    }

    @PutMapping("/{id}/{hodId}")
    public DepartmentGetDto updateDepartment(@PathVariable("id") int deptId, @PathVariable("hodId") int empIdHod) {
        return deptService.updateDepartment(deptId, empIdHod);
    }

    @DeleteMapping("/{id}")
    public String deleteDepartment(@PathVariable("id") int deptId) {
        return deptService.deleteDepartment(deptId);
    }
}
