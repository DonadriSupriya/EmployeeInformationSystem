package com.project.employeesinformationsystem.services;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import com.project.employeesinformationsystem.dto.EmployeeDto;
import com.project.employeesinformationsystem.dto.EmployeePDto;
import com.project.employeesinformationsystem.entities.Department;
import com.project.employeesinformationsystem.entities.Employee;
import com.project.employeesinformationsystem.entities.Job;
import com.project.employeesinformationsystem.entities.JobHistory;
import com.project.employeesinformationsystem.repositories.DepartmentRepository;
import com.project.employeesinformationsystem.repositories.EmployeeRepository;
import com.project.employeesinformationsystem.repositories.JobHistoryRepository;
import com.project.employeesinformationsystem.repositories.JobRepository;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository empRepo;

    @Autowired
    private JobRepository jobRepo;

    @Autowired
    private DepartmentRepository deptRepo;

    @Autowired
    private JobHistoryRepository jobHistoryRepo;

    public EmployeeDto addEmployee(EmployeePDto employeePDto) {
        try {
            Employee emp = new Employee();
            emp.setEmpName(employeePDto.getEmpName());
            emp.setSalary(employeePDto.getSalary());
            emp.setJoinDate(employeePDto.getJoinDate());
            Department dept = deptRepo.findById(employeePDto.getDeptId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            emp.setDepartment(dept);
            Job job = jobRepo.findById(employeePDto.getJobId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            emp.setJob(job);
            empRepo.save(emp);
            EmployeeDto dto = new EmployeeDto();
            dto.setEmpId(emp.getEmpId());
            dto.setEmpName(emp.getEmpName());
            dto.setSalary(emp.getSalary());
            dto.setJoinDate(emp.getJoinDate());
            dto.setDeptId(emp.getDepartment().getDeptId());
            dto.setJobId(emp.getJob().getJobId());
            return dto;
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Data: Provide Valid Input.");
        }
    }

    public List<EmployeeDto> getAllEmp() {
        try {
            List<Employee> emp = empRepo.findAll();
            List<EmployeeDto> dto = new ArrayList<>();
            for (Employee e : emp) {
                EmployeeDto empDto = new EmployeeDto();
                empDto.setEmpId(e.getEmpId());
                empDto.setEmpName(e.getEmpName());
                empDto.setSalary(e.getSalary());
                empDto.setJoinDate(e.getJoinDate());
                empDto.setJobId(e.getJob().getJobId());
                empDto.setDeptId(e.getDepartment().getDeptId());
                dto.add(empDto);
            }
            return dto;
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No employees found.");
        }
    }

    public List<EmployeeDto> getEmpByjob(int jobId) {
        try {
            List<EmployeeDto> dto = new ArrayList<>();
            List<Employee> emp = empRepo.findAllByJobJobId(jobId);
            if (emp.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            } else {
                for (Employee e : emp) {
                    EmployeeDto empDto = new EmployeeDto();
                    empDto.setEmpId(e.getEmpId());
                    empDto.setEmpName(e.getEmpName());
                    empDto.setSalary(e.getSalary());
                    empDto.setJoinDate(e.getJoinDate());
                    empDto.setJobId(e.getJob().getJobId());
                    empDto.setDeptId(e.getDepartment().getDeptId());
                    dto.add(empDto);
                }
                return dto;
            }
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No employee found for job ID" + jobId);
        }
    }

    public List<EmployeeDto> getEmpBydept(int deptId) {
        try {
            List<EmployeeDto> dto = new ArrayList<>();
            List<Employee> emp = empRepo.findAllByDepartmentDeptId(deptId);
            if (emp.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            } else {
                for (Employee e : emp) {
                    EmployeeDto empDto = new EmployeeDto();
                    empDto.setEmpId(e.getEmpId());
                    empDto.setEmpName(e.getEmpName());
                    empDto.setSalary(e.getSalary());
                    empDto.setJoinDate(e.getJoinDate());
                    empDto.setJobId(e.getJob().getJobId());
                    empDto.setDeptId(e.getDepartment().getDeptId());
                    dto.add(empDto);
                }
                return dto;
            }
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, " No employee found for department ID" + deptId);
        }
    }

    public List<EmployeeDto> getEmpByName(String empName) {
        try {
            List<EmployeeDto> dto = new ArrayList<>();
            List<Employee> emp = empRepo.findAllByEmpNameContains(empName);
            if (emp.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            } else {
                for (Employee e : emp) {
                    EmployeeDto empDto = new EmployeeDto();
                    empDto.setEmpId(e.getEmpId());
                    empDto.setEmpName(e.getEmpName());
                    empDto.setSalary(e.getSalary());
                    empDto.setJoinDate(e.getJoinDate());
                    empDto.setJobId(e.getJob().getJobId());
                    empDto.setDeptId(e.getDepartment().getDeptId());
                    dto.add(empDto);
                }
                return dto;
            }
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, " No employee found for name" + empName);

        }

    }

    // 6.List of employees by given salary range
    @GetMapping("/salaryRange/{min}/{max}")
    public List<EmployeeDto> getEmpBySalary(double min, double max) {
        try {
            List<EmployeeDto> dto = new ArrayList<>();
            if (max - min > 0) {
                List<Employee> emp = empRepo.findAllBySalaryBetween(min, max);
                if (emp.isEmpty()) {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND);
                } else {
                    for (Employee e : emp) {
                        EmployeeDto empDto = new EmployeeDto();
                        empDto.setEmpId(e.getEmpId());
                        empDto.setEmpName(e.getEmpName());
                        empDto.setSalary(e.getSalary());
                        empDto.setJoinDate(e.getJoinDate());
                        empDto.setJobId(e.getJob().getJobId());
                        empDto.setDeptId(e.getDepartment().getDeptId());
                        dto.add(empDto);
                    }
                    return dto;
                }
            } else
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        " Invalid input: max salary must be greater than min salary. ");
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    " No employees found within the salary range " + min + " - " + max);
        }
    }

    public List<EmployeeDto> setEmpSalary(int empId, double salary) {
        try {
            Employee emp = empRepo.findById(empId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            List<EmployeeDto> dto = new ArrayList<>();
            emp.setSalary(salary);
            Employee e = empRepo.save(emp);
            EmployeeDto empDto = new EmployeeDto();
            empDto.setEmpId(e.getEmpId());
            empDto.setEmpName(e.getEmpName());
            empDto.setSalary(e.getSalary());
            empDto.setJoinDate(e.getJoinDate());
            empDto.setJobId(e.getJob().getJobId());
            empDto.setDeptId(e.getDepartment().getDeptId());
            dto.add(empDto);
            return dto;
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "An error occurred while updating the department.");
        }
    }

    public String deleteEmp(@PathVariable("id") int empId) {
        try {
            empRepo.findById(empId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            empRepo.deleteById(empId);
            return " employee id " + empId + " deleted successfully.";
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, " Employee ID " + empId + " not found. ");
        }
    }

    public List<EmployeeDto> getExperienceEmployee(int years) {
        try {
            List<Employee> experiencedEmployees = new ArrayList<>();
            List<EmployeeDto> employeeDto = new ArrayList<>();
            int months = 12 * years;
            List<Employee> employees = empRepo.findAll();
            for (Employee employee : employees) {
                int experience = getExperience(employee.getEmpId());
                if (experience >= months) {
                    experiencedEmployees.add(employee);
                }
            }
            if (experiencedEmployees.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                        " No Employee found with experience greater than " + years + " years ");
            }
            for (Employee employee : experiencedEmployees) {
                EmployeeDto dto = new EmployeeDto();
                dto.setEmpId(employee.getEmpId());
                dto.setEmpName(employee.getEmpName());
                dto.setSalary(employee.getSalary());
                dto.setJoinDate(employee.getJoinDate());
                dto.setJobId(employee.getJob().getJobId());
                dto.setDeptId(employee.getDepartment().getDeptId());
                employeeDto.add(dto);
            }
            return employeeDto;
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    " No Employee found with experience greater than " + years + " years ");
        }
    }

    public int getExperience(int empId) {
        List<JobHistory> history = jobHistoryRepo.findAllByEmployeeEmpId(empId);
        int totalMonths = 0;
        for (JobHistory h : history) {
            LocalDate endDate = (h.getEndDate() == null) ? LocalDate.now() : h.getEndDate();
            Period period = Period.between(h.getStartDate(), endDate);
            totalMonths += period.getYears() * 12 + period.getMonths();
        }
        return totalMonths;
    }

}
