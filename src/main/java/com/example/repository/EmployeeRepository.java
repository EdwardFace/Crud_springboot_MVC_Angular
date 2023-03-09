package com.example.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long>{
    void deleteEmployeeById(Long id);
    Optional<Employee> findEmployeeById(Long id);

    @Query(value = "SELECT * FROM empleados WHERE email = :email",nativeQuery = true)
    Optional<Employee> findEmployeeByEmail(@Param("email") String email);
}
