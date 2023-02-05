package com.example.studentservice.repository;

import com.example.studentservice.enums.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "student",schema = "student_management")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;

    @Column(name = "student_name")
    private String studentName;

    @Column(name = "student_email",unique = true)
    private String studentEmail;
    @Column(name = "student_gender")
    @Enumerated(EnumType.STRING)
    private Gender studentGender;

    @Column(name = "student_dob")
    private LocalDate studentDob;

    @Column(name = "student_age")
    @Transient
    private Integer studentAge;

    @Builder.Default
    @Column(name = "student_update_date")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date studentUpdateDate = new Date();

    @Builder.Default
    @Column(name = "student_created_date")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date studentCreateDate = new Date();

    @Column(name="is_deleted")
    private boolean deleted; // Bu alanı true ya çevirince soft delete yapılmış olur.


    public Integer getStudentAge() {
        return Period.between(this.studentDob,LocalDate.now()).getYears();
    }
}
