package com.example.restapidemo.restcrudapidemo.controller;

import com.example.restapidemo.restcrudapidemo.model.Employee;
import com.example.restapidemo.restcrudapidemo.repository.EmployeeRepository;
import com.example.restapidemo.restcrudapidemo.service.EmployeeManagementService;
import com.example.restapidemo.restcrudapidemo.service.EmployeeManagementServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/api/v2/emp")
public class EmployeeManagementControllerV2 {

    @Autowired
    EmployeeManagementServiceImpl employeeManagementServiceImpl;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping(value = "/submitLogin")
    public String submitLogin(@RequestParam("empId") int id, Model model){
        Employee employee = employeeManagementServiceImpl.findSingleEmployeeDetailsById(id);
        model.addAttribute("empName", employee.getEmpName());
        model.addAttribute("empAge", employee.getEmpAge());
        model.addAttribute("empAddress", employee.getEmpAddress());
        return "login";
    }

    @GetMapping("/saveSingleEmployee")
    public String saveSingleEmployee(Model model){
        model.addAttribute("employee", new Employee());
        return "saveemployee";
    }

    @PostMapping(value = "/saveSingleEmployee")
    public String saveSingleEmployee(@ModelAttribute("employee") Employee employee){
        employeeManagementServiceImpl.saveEmployeeDetails(employee);
        return "saveemployee";
    }


    @GetMapping("/deleteSingleEmployee")
    public String deleteSingleEmployee(Model model){
        model.addAttribute("employee", new Employee());
        System.out.println("get delete called......");
        return "deleteemployee";
    }

    @PostMapping(value = "/deleteSingleEmployee")
    public String deleteSingleEmployee(@RequestParam("empId") int empId,
                                       Model model,
                                        HttpSession session){
        System.out.println("delete post api called.....");
        Employee employee = employeeManagementServiceImpl.findSingleEmployeeDetailsById(empId);
        if (employee!=null){
            session.setAttribute("empId", empId);
            session.setAttribute("employee", employee);
            return "deleteemployeebyid";
        }
        else {
            model.addAttribute("msg", "Employee Id not Found");
            return "deleteemployee";
        }
    }

    @PostMapping(value = "/deleteEmployee")
    public String deleteEmployee(@RequestParam("empName") String empName,
                                 Model model,
                                HttpSession session){

        Employee employee = (Employee) session.getAttribute("employee");
        if (employee.getEmpName().equalsIgnoreCase(empName)
                && employee.getEmpId()==(int)session.getAttribute("empId")){
            employeeManagementServiceImpl.deleteSingleEmployee(employee.getEmpId());
            model.addAttribute("msg", "Employee Deleted Successfully");
            return "deleteemployeebyid";
        }else {
            model.addAttribute("msg", "Employee Not Found");
            return "deleteemployeebyid";
        }

    }









}
