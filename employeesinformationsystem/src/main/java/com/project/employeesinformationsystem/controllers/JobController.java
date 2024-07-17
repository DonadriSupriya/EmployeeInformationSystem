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

import com.project.employeesinformationsystem.dto.JobDto;
import com.project.employeesinformationsystem.entities.Job;
import com.project.employeesinformationsystem.services.JobService;

@RestController
@RequestMapping("/job")
public class JobController {
    
    @Autowired
    private JobService jobService;

    @PostMapping("/")
    public JobDto addJob(@RequestBody Job job) {
        return jobService.addJob(job);
    }

    // 1.List of jobs
    @GetMapping("/")
    public List<JobDto> getAllJob() {
        return jobService.getAllJob();  
    }

    @PutMapping("/{id}")
    public JobDto setJob(@PathVariable("id") int jobId, @RequestParam String jobTitle) {
            return jobService.setJob(jobId, jobTitle);
    }

    @DeleteMapping("/{id}")
    public String deleteJob(@PathVariable("id") int jobId) {
            return jobService.deleteJob(jobId);
    }
}
