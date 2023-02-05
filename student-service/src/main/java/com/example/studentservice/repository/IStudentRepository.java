package com.example.studentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IStudentRepository extends JpaRepository<Student, Long> {

    Student getByStudentIdAndDeletedFalse(Long studentId); // is deleted false ise kaydı getir

    List<Student> getAllByDeletedFalse(); // is deleted false ise kayıtları getir
}

