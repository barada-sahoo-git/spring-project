package com.example.restapidemo.restcrudapidemo.repository;


import com.example.restapidemo.restcrudapidemo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findAll();
}
