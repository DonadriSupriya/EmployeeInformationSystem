package com.project.employeesinformationsystem.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.project.employeesinformationsystem.dto.DepartmentGetDto;
import com.project.employeesinformationsystem.entities.Department;
import com.project.employeesinformationsystem.entities.Employee;
import com.project.employeesinformationsystem.repositories.DepartmentRepository;
import com.project.employeesinformationsystem.repositories.EmployeeRepository;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository deptRepo;

    @Autowired
    private EmployeeRepository empRepo;


    public DepartmentGetDto addDepartment(Department department) {
        try{
            Department existingDept = deptRepo.findByDeptName(department.getDeptName());
            if (existingDept != null) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Department name already exists.");
            }
            Department savedDepartment = deptRepo.save(department);
            DepartmentGetDto dto = new DepartmentGetDto();
            dto.setDeptId(savedDepartment.getDeptId());
            dto.setDeptName(savedDepartment.getDeptName());
            return dto;
        }catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Data: Provide Valid Input.");
        }
    }

     public List<DepartmentGetDto> getAllDept() {
        try {
            List<DepartmentGetDto> dto = new ArrayList<>();
            List<Department> dept = deptRepo.findAll();
            for (Department d : dept) {
                DepartmentGetDto d1 = new DepartmentGetDto();
                d1.setDeptId(d.getDeptId());
                d1.setDeptName(d.getDeptName());
                if (d.getEmployee() != null) {
                    d1.setEmpId(d.getEmployee().getEmpId());
                }
                dto.add(d1);
            }
            return dto;
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No departments found.");
        }
    }

    public DepartmentGetDto updateDepartment(int deptId,int empIdHod) {
        try {
            Department dept = deptRepo.findById(deptId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            Employee employee = empRepo.findById(empIdHod)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            dept.setEmployee(employee);
            Department updatedDept = deptRepo.save(dept);
            DepartmentGetDto dto = new DepartmentGetDto();
            dto.setDeptId(updatedDept.getDeptId());
            dto.setDeptName(updatedDept.getDeptName());
            dto.setEmpId(empIdHod);
            return dto;
        }catch (ResponseStatusException e) {
            throw e;
        }catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "An error occurred while updating the department.");
        }
    }

    public String deleteDepartment(int deptId) {
        try {
            deptRepo.findById(deptId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            deptRepo.deleteById(deptId);
            return " department id " + deptId + " deleted successfully. ";
        }catch (ResponseStatusException e) {
            throw e;
        }catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, " department id " + deptId + " not found. ");
        }
    }
}



    

