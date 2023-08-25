package com.example.restapidemo.restcrudapidemo.service;




import com.example.restapidemo.restcrudapidemo.model.Employee;

import java.util.List;

public interface EmployeeManagementService {

    List<Employee> findAllEmployee();
}
