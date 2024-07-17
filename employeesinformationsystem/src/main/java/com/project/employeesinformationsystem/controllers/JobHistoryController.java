package com.project.employeesinformationsystem.controllers;

import java.time.LocalDate;
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

import com.project.employeesinformationsystem.dto.JobHistoryDto;
import com.project.employeesinformationsystem.dto.JobHistoryPDto;
import com.project.employeesinformationsystem.services.JobHistortService;

@RestController
@RequestMapping("/jobhistory")
public class JobHistoryController {
   
    @Autowired
    private JobHistortService jobHisService;

    // List of jobhistory
    @GetMapping("/")
    public List<JobHistoryDto> getAllJobHistory() {
      return jobHisService.getAllJobHistory(); 
    }

    // 8.List job history for given employee
    @GetMapping("/{employeeId}")
    public List<JobHistoryDto> getJobHistoryByemp(@PathVariable("employeeId") int empId) {
                return jobHisService.getJobHistoryByemp(empId);
    }

    @PostMapping("/")
    public JobHistoryPDto addJobHistory(@RequestBody JobHistoryPDto jobHistoryPDto) {
                return jobHisService.addJobHistory(jobHistoryPDto);
            }

    @PutMapping("/{id}/{date}")
    public JobHistoryDto updateEnddate(@PathVariable("id") int jobHistoryId, @PathVariable("date") LocalDate endDate) {
      return jobHisService.updateEnddate(jobHistoryId, endDate);
    }

    @DeleteMapping("/{id}")
    public String deleteJobHistory(@PathVariable("id") int jobHistoryId) {
        return jobHisService.deleteJobHistory(jobHistoryId);
        
    }
}
