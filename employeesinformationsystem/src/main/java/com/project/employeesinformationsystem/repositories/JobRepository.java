package com.project.employeesinformationsystem.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.project.employeesinformationsystem.entities.Job;

public interface JobRepository extends JpaRepository<Job, Integer> {
    
    Job findByJobTitle(String jobTitle);

}
