package com.project.employeesinformationsystem.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.project.employeesinformationsystem.dto.JobDto;
import com.project.employeesinformationsystem.entities.Job;
import com.project.employeesinformationsystem.repositories.JobRepository;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepo;

      public JobDto addJob(Job job) {
        try {
            Job existingJob = jobRepo.findByJobTitle(job.getJobTitle());
            if (existingJob != null) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Job name already exists.");
            }
            jobRepo.save(job);
            JobDto dto = new JobDto();
            dto.setJobId(job.getJobId());
            dto.setJobTitle(job.getJobTitle());
            return dto;
        }catch (ResponseStatusException e) {
            throw e;
        }catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Data: Provide Valid Input.");
        }
    }  
    
     public List<JobDto> getAllJob() {
        try {
            List<Job> jobs = jobRepo.findAll();
            List<JobDto> jobDto = new ArrayList<JobDto>();
            for (Job job : jobs) {
                JobDto dto = new JobDto();
                dto.setJobId(job.getJobId());
                dto.setJobTitle(job.getJobTitle());
                jobDto.add(dto);
            }
            return jobDto;   
        } catch (ResponseStatusException e) {
            throw e;
        }catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Jobs found.");
        }
    }

      public JobDto setJob(int jobId,String jobTitle) {
        try {
            Job job = jobRepo.findById(jobId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            job.setJobTitle(jobTitle);
            jobRepo.save(job);
            JobDto dto = new JobDto();
            dto.setJobId(jobId);
            dto.setJobTitle(jobTitle);
            return dto;
        } catch (ResponseStatusException e) {
            throw e;
        }catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "An error occurred while updating the department.", e);
        }
    }

    public String deleteJob(int jobId) {
        try {
            jobRepo.findById(jobId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            jobRepo.deleteById(jobId);
            return " Job ID " + jobId + " deleted successfully.";
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, " Job ID " + jobId + " not found. ");
        }
    }
}
