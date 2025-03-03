package com.example.demo.Repository;

import com.example.demo.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // Metode custom jika diperlukan
    boolean existsByEmail(String email);
}