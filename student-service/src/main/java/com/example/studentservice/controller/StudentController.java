package com.example.studentservice.controller;

import com.example.studentservice.enums.Language;
import com.example.studentservice.exception.enums.FriendlyMessageCodes;
import com.example.studentservice.exception.utils.FriendlyMessageUtils;
import com.example.studentservice.repository.Student;
import com.example.studentservice.request.StudentCreateRequest;
import com.example.studentservice.request.StudentUpdateRequest;
import com.example.studentservice.response.FriendlyMessage;
import com.example.studentservice.response.InternalApiResponse;
import com.example.studentservice.response.StudentResponse;
import com.example.studentservice.service.IStudentService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping(value = "/api/1.0/student")
@RequiredArgsConstructor
public class StudentController {
    private final IStudentService studentService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/{language}/create")
    public InternalApiResponse<StudentResponse> createProduct(@PathVariable("language") Language language,
                                                              @RequestBody StudentCreateRequest studentCreateRequest)  {
        Student student = studentService.createStudent(language, studentCreateRequest);
        StudentResponse studentResponse = convertStudentResponse(student);
        return InternalApiResponse.<StudentResponse> builder()
                .friendlyMessage(FriendlyMessage.builder()
                        .title(FriendlyMessageUtils.getFriendlyMessage(language, FriendlyMessageCodes.SUCCESS))
                        .description(FriendlyMessageUtils.getFriendlyMessage(language, FriendlyMessageCodes.STUDENT_SUCCESSFULLY_CREATED))
                        .build())
                .httpStatus(HttpStatus.CREATED)
                .hasError(false)
                .payload(studentResponse)
                .build();
    }

    @ResponseStatus(HttpStatus.OK) //get isteği olduğu için ok dedik
    @GetMapping(value = "/{language}/get/{studentId}")
    public InternalApiResponse<StudentResponse> getProduct(@PathVariable("language")Language language,
                                                           @PathVariable("studentId")Long studentId) {
        Student student = studentService.getStudent(language, studentId);
        StudentResponse productResponse = convertStudentResponse(student);
        return InternalApiResponse.<StudentResponse> builder()
                // get isteği olduğu için friendly message yazmadık
                .httpStatus(HttpStatus.OK)
                .hasError(false)
                .payload(productResponse)
                .build();
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{language}/update/{studentId}")
    public InternalApiResponse<StudentResponse> updateProduct(@PathVariable("language")Language language,
                                                              @PathVariable("studentId")Long studentId,
                                                              @RequestBody StudentUpdateRequest studentUpdateRequest) {
        Student student = studentService.updateStudent(language, studentId, studentUpdateRequest);
        StudentResponse productResponse = convertStudentResponse(student);
        return InternalApiResponse.<StudentResponse> builder()
                .friendlyMessage(FriendlyMessage.builder()
                        .title(FriendlyMessageUtils.getFriendlyMessage(language, FriendlyMessageCodes.SUCCESS))
                        .description(FriendlyMessageUtils.getFriendlyMessage(language, FriendlyMessageCodes.STUDENT_SUCCESSFULLY_UPDATED))
                        .build())
                .httpStatus(HttpStatus.OK)
                .hasError(false)
                .payload(productResponse)
                .build();
    }

    @ApiOperation(value = "This endpoint get all students") // swaggerde kullanılan bir anatasyon isteğe bağlı.
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{language}/students")
    public InternalApiResponse<List<StudentResponse>> getAllStudents(@PathVariable("language")Language language) {
        List<Student> students = studentService.getAllStudents(language);
        List<StudentResponse> productResponses = convertStudentResponseList(students);
        return InternalApiResponse.<List<StudentResponse>> builder()
                .httpStatus(HttpStatus.OK)
                .hasError(false)
                .payload(productResponses)
                .build();
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/{language}/delete/{studentId}")
    public InternalApiResponse<StudentResponse> deleteStudent(@PathVariable("language")Language language,
                                                              @PathVariable("studentId")Long studentId) {
        Student student = studentService.deleteStudent(language, studentId);
        StudentResponse studentResponse = convertStudentResponse(student);
        return InternalApiResponse.<StudentResponse> builder()
                .friendlyMessage(FriendlyMessage.builder()
                        .title(FriendlyMessageUtils.getFriendlyMessage(language, FriendlyMessageCodes.SUCCESS))
                        .description(FriendlyMessageUtils.getFriendlyMessage(language, FriendlyMessageCodes.STUDENT_SUCCESSFULLY_DELETED))
                        .build())
                .httpStatus(HttpStatus.OK)
                .hasError(false)
                .build();
    }

    private List<StudentResponse> convertStudentResponseList(List<Student> students) {
        return students.stream().map(this::convertStudentResponse).collect(Collectors.toList());
    }

    private StudentResponse convertStudentResponse(Student student) {
        return StudentResponse.builder()
                .studentId(student.getStudentId())
                .studentName(student.getStudentName())
                .studentEmail(student.getStudentEmail())
                .studentGender(student.getStudentGender())
                .studentDob(student.getStudentDob())
                .studentAge(student.getStudentAge())
                .studentCreatedDate(student.getStudentCreateDate().getTime())
                .studentUpdatedDate(student.getStudentUpdateDate().getTime())
                .build();

    }
}
