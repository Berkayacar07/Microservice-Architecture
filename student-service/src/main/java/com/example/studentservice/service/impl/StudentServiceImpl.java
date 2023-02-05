package com.example.studentservice.service.impl;

import com.example.studentservice.enums.Language;
import com.example.studentservice.exception.enums.FriendlyMessageCodes;
import com.example.studentservice.exception.exceptions.StudentAlreadyDeletedException;
import com.example.studentservice.exception.exceptions.StudentNotCreatedException;
import com.example.studentservice.exception.exceptions.StudentNotFoundException;
import com.example.studentservice.repository.IStudentRepository;
import com.example.studentservice.repository.Student;
import com.example.studentservice.request.StudentCreateRequest;
import com.example.studentservice.request.StudentUpdateRequest;
import com.example.studentservice.service.IStudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements IStudentService {
    private final IStudentRepository studentRepository;
    @Override
    public Student createStudent(Language language, StudentCreateRequest studentCreateRequest) {
        try {
            Student product = Student.builder()
                    .studentName(studentCreateRequest.getStudentName())
                    .studentEmail(studentCreateRequest.getStudentEmail())
                    .studentGender(studentCreateRequest.getStudentGender())
                    .studentDob(studentCreateRequest.getStudentDob())
                    .deleted(false)
                    .build();
            Student productResponse = studentRepository.save(product);
            return productResponse;
        } catch (Exception e) {
            throw new StudentNotCreatedException(language, FriendlyMessageCodes.STUDENT_NOT_CREATED_EXCEPTION,
                    "Student request: " + studentCreateRequest.toString() + " could not be created");
        }
    }

    @Override
    public Student getStudent(Language language, Long productId) {
        Student product = studentRepository.getByStudentIdAndDeletedFalse(productId);
        if(Objects.isNull(product)){
            throw new StudentNotFoundException(language, FriendlyMessageCodes.STUDENT_NOT_FOUND_EXCEPTION,
                    "Student with productId: " + productId + " not found");
        }
        return product;
    }

    @Override
    public List<Student> getAllStudents(Language language) {
        List<Student> products = studentRepository.getAllByDeletedFalse();
        if(products.isEmpty()){
            throw new StudentNotFoundException(language, FriendlyMessageCodes.STUDENT_NOT_FOUND_EXCEPTION,
                    "Students not found");
        }
        return products;
    }

    @Override
    public Student updateStudent(Language language, Long productId, StudentUpdateRequest studentUpdateRequest) {
        Student student = getStudent(language, productId);
        student.setStudentName(studentUpdateRequest.getStudentName());
        student.setStudentEmail(studentUpdateRequest.getStudentEmail());
        student.setStudentGender(studentUpdateRequest.getStudentGender());
        student.setStudentDob(studentUpdateRequest.getStudentDob());
        student.setStudentCreateDate(student.getStudentCreateDate());
        student.setStudentUpdateDate(new Date());
        Student studentResponse = studentRepository.save(student);
        return studentResponse;
    }

    @Override
    public Student deleteStudent(Language language, Long studentId) {
        Student product;
        try {
            product = getStudent(language, studentId);
            product.setDeleted(true);
            product.setStudentUpdateDate(new Date());
            Student studentResponse = studentRepository.save(product);
            return studentResponse;
        } catch (Exception e) {
            throw new StudentAlreadyDeletedException(language, FriendlyMessageCodes.STUDENT_ALREADY_DELETED,
                    "Student already deleted productId: " + studentId);
        }
    }
}
