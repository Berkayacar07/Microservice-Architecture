package com.example.studentservice.response;

import com.example.studentservice.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
public class StudentResponse {
    private Long studentId;
    private String studentName;
    private String studentEmail;
    private Gender studentGender;
    private LocalDate studentDob;
    private Integer studentAge;
    private Long studentCreatedDate;
    private Long studentUpdatedDate;
}
