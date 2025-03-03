package com.example.demo.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employees")
public class Employee {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nama;
    
    @Column(nullable = false, unique = true)
    private String email;
    
    @Column(name = "nomor_telepon")
    private String nomorTelepon;
    
    @Column(name = "tanggal_lahir")
    private LocalDate tanggalLahir;
    
    @Column(nullable = false)
    private String jabatan;
    
    @Column(name = "tanggal_bergabung")
    private LocalDate tanggalBergabung;
    
    private Double gaji;
    
    private String alamat;
}