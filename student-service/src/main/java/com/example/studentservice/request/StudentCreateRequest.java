package com.example.studentservice.request;

import com.example.studentservice.enums.Gender;
import lombok.Data;

import java.time.LocalDate;

@Data
public class StudentCreateRequest {

    private String studentName;
    private String studentEmail;
    private Gender studentGender;
    private LocalDate studentDob;
}
