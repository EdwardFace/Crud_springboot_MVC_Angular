package com.example.services.interfaces;

import java.lang.StackWalker.Option;
import java.util.List;
import java.util.Optional;

import com.example.model.Employee;

public interface EmployeeServiceInterface {
    public boolean addEmployee(Employee e);

    public List<Employee>findAllEmployees();

    public boolean updateEmployee(Employee e);

    public void deleteEmployee(Long id);

    public Optional<Employee> findEmployeeByEmail(String email);

    public Optional<Employee> findEmployeeById(Long id);
}
