package com.emreblbl.springbootthymeleaf.demo.dao;

import com.emreblbl.springbootthymeleaf.demo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    // add a method to sort by last Name
    public List<Employee> findAllByOrderByLastNameAsc(); // it's created automatically by JPA and find ALL from Employee order by LastName asc.
}
