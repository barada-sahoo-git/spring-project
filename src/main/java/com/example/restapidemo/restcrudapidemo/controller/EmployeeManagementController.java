package com.example.restapidemo.restcrudapidemo.controller;



import com.example.restapidemo.restcrudapidemo.model.Employee;
import com.example.restapidemo.restcrudapidemo.service.EmployeeManagementServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/emp")
public class EmployeeManagementController {


    @Autowired
    EmployeeManagementServiceImpl employeeManagementServiceImpl;

    @GetMapping(value = "/findallemployees")
    public List<Employee> getAllEmployee() {
        System.out.println("controller executed....");
        return employeeManagementServiceImpl.findAllEmployee();
    }

    @GetMapping(value = "/getemployee/{empId}")
    public Employee getSingleEmployeeDetailsById(@PathVariable int empId){
       return employeeManagementServiceImpl.findSingleEmployeeDetailsById(empId);
    }

    @DeleteMapping(value = "/deleteemployee/{empId}")
    public void deleteSingleEmployeeById(@PathVariable int empId){
        employeeManagementServiceImpl.deleteSingleEmployee(empId);
    }

    @PostMapping(value = "/saveemployee/{ename}/{eage}/{eaddr}")
    public void saveSingleEmployeeDetails(@PathVariable("ename") String empName,
                                          @PathVariable("eage") int empAge,
                                          @PathVariable("eaddr") String empAddress){

        employeeManagementServiceImpl.saveSingleEmployeeDetails(empName, empAge, empAddress);
    }

    @PutMapping(value = "/updateEmployeeAddress/{empId}/{addr}")
    public void updateSingleEmployeeDetails(@PathVariable("empId") int id,
                                             @PathVariable("addr") String empAddress){
        employeeManagementServiceImpl.updateSingleEmployeeDetails(id, empAddress);
    }

    @PostMapping(value = "/savenewemployee")
    public void saveEmployeeDetails(@Valid @RequestBody Employee employee){
        employeeManagementServiceImpl.saveEmployeeDetails(employee);
    }
}
