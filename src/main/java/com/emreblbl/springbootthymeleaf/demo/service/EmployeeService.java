package com.emreblbl.springbootthymeleaf.demo.service;

import com.emreblbl.springbootthymeleaf.demo.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

public interface EmployeeService {
    public List<Employee> findAll();
    public Employee findById(int theId);
    public void save(Employee employee);
    public void delete(int theId);

}
