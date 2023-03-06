package com.example.services;

import java.lang.StackWalker.Option;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.exceptions.UserNotFoundException;
import com.example.model.Employee;
import com.example.repository.EmployeeRepository;
import com.example.services.interfaces.EmployeeServiceInterface;

@Service
public class EmployeeService implements EmployeeServiceInterface{
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public boolean addEmployee(Employee e) {
        e.setEmployeeCode(UUID.randomUUID().toString());
        try{
            employeeRepository.saveAndFlush(e);
            return true;
        }catch(Exception ex){
            return false;
        }
        

    }

    @Override
    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public boolean updateEmployee(Employee e) {
        try{
            employeeRepository.save(e);
            return true;
        }catch(Exception ex){
            return false;
        }
        
    }

    @Override
    public void deleteEmployee(Long id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if(employeeOptional.isPresent()){
            employeeRepository.deleteEmployeeById(id);
        }
    }

    @Override
    public Optional<Employee> findEmployeeById(Long id) {
        try{
            return employeeRepository.findEmployeeById(id);
        }catch(UserNotFoundException ex){
            return Optional.empty();
        }
        
        
    }
    


}
