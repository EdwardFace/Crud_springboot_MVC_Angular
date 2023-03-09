package com.example.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.exceptions.UserNotFoundException;
import com.example.model.Employee;
import com.example.repository.EmployeeRepository;
import com.example.services.interfaces.EmployeeServiceInterface;

@Service
@Transactional
public class EmployeeService implements EmployeeServiceInterface{
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public boolean addEmployee(Employee e) {
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
    public Optional<Employee> findEmployeeByEmail(String email) {
        try{
            return employeeRepository.findEmployeeByEmail(email);
        }catch(UserNotFoundException ex){
            return Optional.empty();
        } 
    }

    @Override
    public Optional<Employee> findEmployeeById(Long id) {
        try{
            return employeeRepository.findById(id);
        }catch(UserNotFoundException ex){
            return Optional.empty();
        }
        
    }

    public String setCodeEmployee(){
        return UUID.randomUUID().toString();
    }
    


}
