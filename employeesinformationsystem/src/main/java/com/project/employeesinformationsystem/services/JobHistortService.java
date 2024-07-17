package com.project.employeesinformationsystem.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.project.employeesinformationsystem.dto.JobHistoryDto;
import com.project.employeesinformationsystem.dto.JobHistoryPDto;
import com.project.employeesinformationsystem.entities.Employee;
import com.project.employeesinformationsystem.entities.Job;
import com.project.employeesinformationsystem.entities.JobHistory;
import com.project.employeesinformationsystem.repositories.EmployeeRepository;
import com.project.employeesinformationsystem.repositories.JobHistoryRepository;
import com.project.employeesinformationsystem.repositories.JobRepository;

@Service
public class JobHistortService {

    @Autowired
    private JobHistoryRepository jobHistoryRepo;

     @Autowired
    private JobRepository jobRepo;

    @Autowired
    private EmployeeRepository empRepo;


     public List<JobHistoryDto> getAllJobHistory() {
        try {
            List<JobHistory> jobHistory = jobHistoryRepo.findAll();
            List<JobHistoryDto> jhDto = new ArrayList<>();
            for (JobHistory jh : jobHistory) {
                JobHistoryDto dto = new JobHistoryDto();
                dto.setJobHistoryId(jh.getJobHistoryId());
                dto.setStartDate(jh.getStartDate());
                dto.setEndDate(jh.getEndDate());
                dto.setJobId(jh.getJob().getJobId());
                dto.setEmpId(jh.getEmployee().getEmpId());
                jhDto.add(dto);
            }
            return jhDto;
        }catch (ResponseStatusException e) {
            throw e;
        }catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "joB History  not found.");
        }
    }
    
    public List<JobHistoryDto> getJobHistoryByemp(int empId) {
        try {
            List<JobHistory> jobHistory = jobHistoryRepo.findAllByEmployeeEmpId(empId);
            if (jobHistory.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            } else {
                List<JobHistoryDto> jhDto = new ArrayList<>();
                for (JobHistory jh : jobHistory) {
                    JobHistoryDto dto = new JobHistoryDto();
                    dto.setJobHistoryId(jh.getJobHistoryId());
                    dto.setStartDate(jh.getStartDate());
                    dto.setEndDate(jh.getEndDate());
                    dto.setJobId(jh.getJob().getJobId());
                    dto.setEmpId(jh.getEmployee().getEmpId());
                    jhDto.add(dto);
                }
                return jhDto;
            }
        }catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, " Job History not found for employee id " + empId);
        }
    }

     public JobHistoryPDto addJobHistory(JobHistoryPDto jobHistoryPDto) {
        try {
            JobHistory jobHistory = new JobHistory();
            LocalDate startDate = jobHistoryPDto.getStartDate();
            LocalDate endDate = jobHistoryPDto.getEndDate();
            if (endDate != null && startDate.isAfter(endDate) ) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Start date cannot be after end date.");
            } else {
                jobHistory.setStartDate(startDate);
                jobHistory.setEndDate(endDate);
                Job job = jobRepo.findById(jobHistoryPDto.getJobId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
                jobHistory.setJob(job);
                Employee emp = empRepo.findById(jobHistoryPDto.getEmpId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
                jobHistory.setEmployee(emp);
                JobHistory savedJobHistory = jobHistoryRepo.save(jobHistory);
                JobHistoryPDto dto = new JobHistoryPDto();
                dto.setStartDate(savedJobHistory.getStartDate());
                dto.setEndDate(savedJobHistory.getEndDate());
                dto.setJobId(savedJobHistory.getJob().getJobId());
                dto.setEmpId(savedJobHistory.getEmployee().getEmpId());
                return dto;
            }
        }catch (ResponseStatusException e) {
            throw e;
        }catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Data: Provide Valid Input.");
        }
    }

    public JobHistoryDto updateEnddate(int jobHistoryId,LocalDate endDate) {
        try {
            JobHistory jobHistory = jobHistoryRepo.findById(jobHistoryId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            LocalDate startDate = jobHistory.getStartDate();
            if (startDate.isAfter(endDate) || startDate.isEqual(endDate)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Start date cannot be after end date.");
            } else {
                jobHistory.setEndDate(endDate);
                jobHistoryRepo.save(jobHistory);
                JobHistoryDto dto = new JobHistoryDto();
                dto.setEmpId(jobHistory.getEmployee().getEmpId());
                dto.setJobId(jobHistory.getJob().getJobId());
                dto.setEndDate(endDate);
                dto.setStartDate(jobHistory.getStartDate());
                dto.setJobHistoryId(jobHistoryId);
                return dto;
            }
        }catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "An error occurred while updating the department.");
        }
    }

    public String deleteJobHistory(int jobHistoryId) {
        try {
            jobHistoryRepo.findById(jobHistoryId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            jobHistoryRepo.deleteById(jobHistoryId);
            return " Job History Id " + jobHistoryId + " deleted successfully.";
        }catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, " Job History Id " + jobHistoryId + " not found.");
        }
    }


}
