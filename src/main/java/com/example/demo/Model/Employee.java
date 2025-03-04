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
    @Column(name = "nik", nullable = false)
    private Long nik;
    
    @Column(name = "nama_lengkap", nullable = false)
    private String namaLengkap;
    
    @Column(name = "jenis_kelamin", nullable = false)
    @Enumerated(EnumType.STRING)
    private JenisKelamin jenisKelamin;
    
    @Column(name = "tanggal_lahir")
    private LocalDate tanggalLahir;
    
    @Column(name = "alamat", columnDefinition = "TEXT")
    private String alamat;
    
    @Column(name = "negara", nullable = false)
    private String negara;
    
    public enum JenisKelamin {
        LAKI_LAKI,
        PEREMPUAN
    }
}