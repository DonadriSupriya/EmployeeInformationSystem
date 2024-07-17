package com.project.employeesinformationsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.employeesinformationsystem.entities.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    Department findByDeptName(String deptName);

}
