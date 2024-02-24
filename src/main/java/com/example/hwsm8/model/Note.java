package com.example.hwsm8.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "notes")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 40)
    private String title;
    @Column(nullable = false, length = 150)
    private String content;
    private LocalDateTime localDateTime = LocalDateTime.now();
}
