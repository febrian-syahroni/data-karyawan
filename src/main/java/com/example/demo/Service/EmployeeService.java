package com.example.demo.Service;

import com.example.demo.DTO.EmployeeDTO;
import com.example.demo.Model.Employee;
import com.example.demo.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    
    private final EmployeeRepository employeeRepository;
    
    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    
    // Mendapatkan semua karyawan
    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    // Mendapatkan karyawan berdasarkan ID
    public EmployeeDTO getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Karyawan dengan ID " + id + " tidak ditemukan"));
        return convertToDTO(employee);
    }
    
    // Membuat karyawan baru
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        if (employeeDTO.getId() != null && employeeRepository.existsById(employeeDTO.getId())) {
            throw new RuntimeException("Karyawan dengan ID " + employeeDTO.getId() + " sudah ada");
        }
        
        if (employeeRepository.existsByEmail(employeeDTO.getEmail())) {
            throw new RuntimeException("Email " + employeeDTO.getEmail() + " sudah digunakan");
        }
        
        Employee employee = convertToEntity(employeeDTO);
        Employee savedEmployee = employeeRepository.save(employee);
        return convertToDTO(savedEmployee);
    }
    
    // Memperbarui data karyawan
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO) {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Karyawan dengan ID " + id + " tidak ditemukan"));
        
        // Check if email is being changed and if it's already in use
        if (!existingEmployee.getEmail().equals(employeeDTO.getEmail()) && 
                employeeRepository.existsByEmail(employeeDTO.getEmail())) {
            throw new RuntimeException("Email " + employeeDTO.getEmail() + " sudah digunakan");
        }
        
        // Update properties
        existingEmployee.setNama(employeeDTO.getNama());
        existingEmployee.setEmail(employeeDTO.getEmail());
        existingEmployee.setNomorTelepon(employeeDTO.getNomorTelepon());
        existingEmployee.setTanggalLahir(employeeDTO.getTanggalLahir());
        existingEmployee.setJabatan(employeeDTO.getJabatan());
        existingEmployee.setTanggalBergabung(employeeDTO.getTanggalBergabung());
        existingEmployee.setGaji(employeeDTO.getGaji());
        existingEmployee.setAlamat(employeeDTO.getAlamat());
        
        Employee updatedEmployee = employeeRepository.save(existingEmployee);
        return convertToDTO(updatedEmployee);
    }
    
    // Menghapus karyawan
    public void deleteEmployee(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new RuntimeException("Karyawan dengan ID " + id + " tidak ditemukan");
        }
        employeeRepository.deleteById(id);
    }
    
    // Konversi Entity ke DTO
    private EmployeeDTO convertToDTO(Employee employee) {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(employee.getId());
        dto.setNama(employee.getNama());
        dto.setEmail(employee.getEmail());
        dto.setNomorTelepon(employee.getNomorTelepon());
        dto.setTanggalLahir(employee.getTanggalLahir());
        dto.setJabatan(employee.getJabatan());
        dto.setTanggalBergabung(employee.getTanggalBergabung());
        dto.setGaji(employee.getGaji());
        dto.setAlamat(employee.getAlamat());
        return dto;
    }
    
    // Konversi DTO ke Entity
    private Employee convertToEntity(EmployeeDTO dto) {
        Employee employee = new Employee();
        employee.setId(dto.getId());
        employee.setNama(dto.getNama());
        employee.setEmail(dto.getEmail());
        employee.setNomorTelepon(dto.getNomorTelepon());
        employee.setTanggalLahir(dto.getTanggalLahir());
        employee.setJabatan(dto.getJabatan());
        employee.setTanggalBergabung(dto.getTanggalBergabung());
        employee.setGaji(dto.getGaji());
        employee.setAlamat(dto.getAlamat());
        return employee;
    }
}