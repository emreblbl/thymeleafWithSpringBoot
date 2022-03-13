package com.emreblbl.springbootthymeleaf.demo.controller;


import com.emreblbl.springbootthymeleaf.demo.entity.Employee;
import com.emreblbl.springbootthymeleaf.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    private EmployeeService employeeService;
    @Autowired
    public EmployeeController(EmployeeService theEmployeeService){
        this.employeeService = theEmployeeService;
    }

    @GetMapping("/list")
    public String listEmployees(Model model){
        List<Employee> employeeList = employeeService.findAll();
        // add to the spring model
        model.addAttribute("employees",employeeList);

        return "employees/list-employees";
    }
    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel){
        Employee tempEmployee = new Employee();
        theModel.addAttribute("employee",tempEmployee);

        return "employees/employee-form";
    }
    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee")Employee employee){
        // save the employee
        employeeService.save(employee);
        // use a redirect to prevent duplicate submission

        return "redirect:/employees/list";
    }
    // add mapping for "/list"
    @GetMapping("showFormForUpdate")
    public String showFormForUpdate(@RequestParam("employeeId")int theId,Model theModel){

        theModel.addAttribute("employee",employeeService.findById(theId));


        return "employees/employee-form";
    }
    @GetMapping("delete")
    public String delete(@RequestParam("employeeId")int theId){
        employeeService.delete(theId);
        return "redirect:/employees/list";
    }
}
