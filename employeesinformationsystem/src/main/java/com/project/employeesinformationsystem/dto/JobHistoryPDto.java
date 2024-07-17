package com.project.employeesinformationsystem.dto;

import java.time.LocalDate;

public class JobHistoryPDto {
    private int empId;
    private int jobId;
    private LocalDate startDate;
    private LocalDate endDate;

    public JobHistoryPDto() {
    }

    public JobHistoryPDto(int empId, int jobId, LocalDate startDate, LocalDate endDate) {
        this.empId = empId;
        this.jobId = jobId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "JobHistoryPutDto [empId=" + empId + ", jobId=" + jobId + ", startDate=" + startDate + ", endDate="
                + endDate + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + empId;
        result = prime * result + jobId;
        result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
        result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        JobHistoryPDto other = (JobHistoryPDto) obj;
        if (empId != other.empId)
            return false;
        if (jobId != other.jobId)
            return false;
        if (startDate == null) {
            if (other.startDate != null)
                return false;
        } else if (!startDate.equals(other.startDate))
            return false;
        if (endDate == null) {
            if (other.endDate != null)
                return false;
        } else if (!endDate.equals(other.endDate))
            return false;
        return true;
    }

    
    






}
