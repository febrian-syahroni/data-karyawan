package com.example.demo.Controller;

import com.example.demo.DTO.EmployeeDTO;
import com.example.demo.Model.Employee.JenisKelamin;
import com.example.demo.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "*")
public class EmployeeController {
    
    private final EmployeeService employeeService;
    
    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    
    // GET: Dapatkan semua karyawan
    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        List<EmployeeDTO> employees = employeeService.getAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
    
    // GET: Dapatkan karyawan berdasarkan NIK
    @GetMapping("/{nik}")
    public ResponseEntity<EmployeeDTO> getEmployeeByNik(@PathVariable Long nik) {
        EmployeeDTO employee = employeeService.getEmployeeByNik(nik);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }
    
    // GET: Dapatkan daftar jenis kelamin
    @GetMapping("/jenis-kelamin")
    public ResponseEntity<List<JenisKelamin>> getJenisKelaminList() {
        return new ResponseEntity<>(Arrays.asList(JenisKelamin.values()), HttpStatus.OK);
    }
    
    // POST: Buat karyawan baru
    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO createdEmployee = employeeService.createEmployee(employeeDTO);
        return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
    }
    
    // PUT: Perbarui karyawan
    @PutMapping("/{nik}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Long nik, @RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO updatedEmployee = employeeService.updateEmployee(nik, employeeDTO);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }
    
    // DELETE: Hapus karyawan
    @DeleteMapping("/{nik}")
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long nik) {
        employeeService.deleteEmployee(nik);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    // Penanganan exception
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleExceptions(RuntimeException e) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}