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
    
    // Mendapatkan karyawan berdasarkan NIK
    public EmployeeDTO getEmployeeByNik(Long nik) {
        Employee employee = employeeRepository.findById(nik)
                .orElseThrow(() -> new RuntimeException("Karyawan dengan NIK " + nik + " tidak ditemukan"));
        return convertToDTO(employee);
    }
    
    // Membuat karyawan baru
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        if (employeeRepository.existsById(employeeDTO.getNik())) {
            throw new RuntimeException("Karyawan dengan NIK " + employeeDTO.getNik() + " sudah ada");
        }
        
        Employee employee = convertToEntity(employeeDTO);
        Employee savedEmployee = employeeRepository.save(employee);
        return convertToDTO(savedEmployee);
    }
    
    // Memperbarui data karyawan
    public EmployeeDTO updateEmployee(Long nik, EmployeeDTO employeeDTO) {
        Employee existingEmployee = employeeRepository.findById(nik)
                .orElseThrow(() -> new RuntimeException("Karyawan dengan NIK " + nik + " tidak ditemukan"));
        
        // Update properties
        existingEmployee.setNamaLengkap(employeeDTO.getNamaLengkap());
        existingEmployee.setJenisKelamin(employeeDTO.getJenisKelamin());
        existingEmployee.setTanggalLahir(employeeDTO.getTanggalLahir());
        existingEmployee.setAlamat(employeeDTO.getAlamat());
        existingEmployee.setNegara(employeeDTO.getNegara());
        
        Employee updatedEmployee = employeeRepository.save(existingEmployee);
        return convertToDTO(updatedEmployee);
    }
    
    // Menghapus karyawan
    public void deleteEmployee(Long nik) {
        if (!employeeRepository.existsById(nik)) {
            throw new RuntimeException("Karyawan dengan NIK " + nik + " tidak ditemukan");
        }
        employeeRepository.deleteById(nik);
    }
    
    // Konversi Entity ke DTO
    private EmployeeDTO convertToDTO(Employee employee) {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setNik(employee.getNik());
        dto.setNamaLengkap(employee.getNamaLengkap());
        dto.setJenisKelamin(employee.getJenisKelamin());
        dto.setTanggalLahir(employee.getTanggalLahir());
        dto.setAlamat(employee.getAlamat());
        dto.setNegara(employee.getNegara());
        return dto;
    }
    
    // Konversi DTO ke Entity
    private Employee convertToEntity(EmployeeDTO dto) {
        Employee employee = new Employee();
        employee.setNik(dto.getNik());
        employee.setNamaLengkap(dto.getNamaLengkap());
        employee.setJenisKelamin(dto.getJenisKelamin());
        employee.setTanggalLahir(dto.getTanggalLahir());
        employee.setAlamat(dto.getAlamat());
        employee.setNegara(dto.getNegara());
        return employee;
    }
}