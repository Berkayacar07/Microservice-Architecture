package com.example.studentservice.service;


import com.example.studentservice.enums.Language;
import com.example.studentservice.repository.Student;
import com.example.studentservice.request.StudentCreateRequest;
import com.example.studentservice.request.StudentUpdateRequest;

import java.util.List;

public interface IStudentService {

    Student createStudent(Language language, StudentCreateRequest studentCreateRequest);

    Student getStudent(Language language, Long studentId);

    List<Student> getAllStudents(Language language);

    Student updateStudent(Language language, Long studentId, StudentUpdateRequest studentUpdateRequest);

    Student deleteStudent(Language language, Long studentId);
}
