package com.project.employeesinformationsystem.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.employeesinformationsystem.entities.JobHistory;

public interface JobHistoryRepository extends JpaRepository<JobHistory, Integer> {

    // 8.List job history for given employee
    List<JobHistory> findAllByEmployeeEmpId(int empId);

}
