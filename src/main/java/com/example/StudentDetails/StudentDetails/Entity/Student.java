package com.example.StudentDetails.StudentDetails.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "students", 
       uniqueConstraints = { @UniqueConstraint(columnNames = {"name", "year", "department"}) }) // Prevents duplicate records
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment primary key
    private Long id;

    @Column(name = "name", nullable = false, length = 100) // Name cannot be null, max 100 characters
    private String name;

    @Column(name = "year", nullable = false) // Year cannot be null
    private int year;

    @Column(name = "department", nullable = false, length = 50) // Department cannot be null, max 50 characters
    private String department;

  
    //  Constructor without ID (Used when creating a new student)
    public Student(String name, int year, String department) {
        this.name = name;
        this.year = year;
        this.department = department;
    }
}

