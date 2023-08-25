package com.example.restapidemo.restcrudapidemo.service;


import com.example.restapidemo.restcrudapidemo.model.Employee;
import com.example.restapidemo.restcrudapidemo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeManagementServiceImpl implements EmployeeManagementService{

    @Autowired(required = false)
    EmployeeRepository employeeRepository;

        public List<Employee> findAllEmployee() {
            return (List<Employee>) employeeRepository.findAll();
        }

        public Employee findSingleEmployeeDetailsById(int empId){
            Optional<Employee> emp = employeeRepository.findById(empId);
            if (emp.isPresent()){
                Employee getEmp = emp.get();
                return getEmp;
            }
            else{
                System.out.println("employee with id " +empId+ "does not exist in the database");
                return null;
            }

        }

    public void deleteSingleEmployee(int empId){
        employeeRepository.deleteById(empId);
    }

    public void saveSingleEmployeeDetails(String empName, int empAge, String empAddress){
            Employee emp = new Employee();
            emp.setEmpName(empName);
            emp.setEmpAge(empAge);
            emp.setEmpAddress(empAddress);
            employeeRepository.save(emp);
    }

    public void updateSingleEmployeeDetails(int empId, String address){
        Employee updateEmployee = new Employee();
        Optional<Employee> employee = employeeRepository.findById(empId);
        if (employee.isPresent()){
            Employee emp = employee.get();
            updateEmployee.setEmpId(emp.getEmpId());
            updateEmployee.setEmpAddress(address);
            updateEmployee.setEmpName(emp.getEmpName());
            updateEmployee.setEmpAge(emp.getEmpAge());
        }
        else {
            System.out.println("employee not found....");
        }

        employeeRepository.save(updateEmployee);
    }

    public void saveEmployeeDetails(Employee employee){
            employeeRepository.save(employee);
    }


}


