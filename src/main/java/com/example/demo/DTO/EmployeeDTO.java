package com.example.demo.DTO;

import com.example.demo.Model.Employee.JenisKelamin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    private Long nik;
    private String namaLengkap;
    private JenisKelamin jenisKelamin;
    private LocalDate tanggalLahir;
    private String alamat;
    private String negara;
}