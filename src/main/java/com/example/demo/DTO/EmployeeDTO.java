package com.example.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    private Long id;
    private String nama;
    private String email;
    private String nomorTelepon;
    private LocalDate tanggalLahir;
    private String jabatan;
    private LocalDate tanggalBergabung;
    private Double gaji;
    private String alamat;
}