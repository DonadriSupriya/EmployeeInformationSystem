package com.project.employeesinformationsystem.dto;

import java.time.LocalDate;

public class EmployeePDto {
    private String empName;
    private double salary;
    private LocalDate joinDate;
    private int jobId;
    private int deptId;

    public EmployeePDto() {
    }

    public EmployeePDto(String empName, double salary, LocalDate joinDate, int jobId, int deptId) {
        this.empName = empName;
        this.salary = salary;
        this.joinDate = joinDate;
        this.jobId = jobId;
        this.deptId = deptId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(LocalDate joinDate) {
        this.joinDate = joinDate;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    @Override
    public String toString() {
        return "EmployeePutDto [empName=" + empName + ", salary=" + salary + ", joinDate=" + joinDate + ", jobId="
                + jobId + ", deptId=" + deptId + "]";
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((empName == null) ? 0 : empName.hashCode());
        long temp;
        temp = Double.doubleToLongBits(salary);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((joinDate == null) ? 0 : joinDate.hashCode());
        result = prime * result + jobId;
        result = prime * result + deptId;
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
        EmployeePDto other = (EmployeePDto) obj;
        if (empName == null) {
            if (other.empName != null)
                return false;
        } else if (!empName.equals(other.empName))
            return false;
        if (Double.doubleToLongBits(salary) != Double.doubleToLongBits(other.salary))
            return false;
        if (joinDate == null) {
            if (other.joinDate != null)
                return false;
        } else if (!joinDate.equals(other.joinDate))
            return false;
        if (jobId != other.jobId)
            return false;
        if (deptId != other.deptId)
            return false;
        return true;
    }
    

   
    


}
