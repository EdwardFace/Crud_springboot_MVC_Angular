package com.example.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Employee;
import com.example.services.EmployeeService;

import lombok.extern.java.Log;

@RestController
@RequestMapping(value = "/empleados")
public class EmployeeController {
    
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/lista")
    public ResponseEntity<List<Employee>> getAllEmployee(){
        List<Employee> employees = employeeService.findAllEmployees();
        return new ResponseEntity<>(employees,HttpStatus.OK);
        /*if(!employees.isEmpty()){
            return new ResponseEntity<>(employees,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }*/
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Employee>getEmployeeById(@PathVariable("id") Long id){
        Employee employee = employeeService.findEmployeeById(id).get();
        if(employee != null){
            return new ResponseEntity<>(employee,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee e){
        try{
            Optional<Employee> employee =  employeeService.findEmployeeByEmail(e.getEmail());
            
            if(!employee.isPresent()){
                e.setEmployee_code(employeeService.setCodeEmployee());
                boolean add = employeeService.addEmployee(e);
                if(add){
                    return new ResponseEntity<Employee>(e, HttpStatus.CREATED);
                }else{
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch(Exception ex){
            
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);   
        }
       
    }
    @PutMapping("/update")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee e){
        Employee employee =  employeeService.findEmployeeById(e.getId()).get();
        if(employee == null){
            boolean add = employeeService.updateEmployee(employee);
            if(add){
                return new ResponseEntity<Employee>(employee, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }            
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") Long id){
        Employee employee =  employeeService.findEmployeeById(id).get();
        if(employee != null){
            employeeService.deleteEmployee(id);
            
            return new ResponseEntity<>(HttpStatus.OK);
            
            

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
