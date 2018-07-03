package com.codegym.emp.service;

import com.codegym.emp.model.Employee;

import java.util.List;

public interface EmployeeService {
    Employee save(Employee employee);

    Employee findById(Long id);

    void delete(Long id);

    Iterable<Employee> findAll();
}
