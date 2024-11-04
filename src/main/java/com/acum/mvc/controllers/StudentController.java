package com.acum.mvc.controllers;

import com.acum.mvc.requests.StudentRequest;
import com.acum.mvc.responses.StudentResponse;
import com.acum.services.Interfaces.IStudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/students")
public class StudentController
{
    private final IStudentService studentService;

    @PostMapping("/add")
    public ResponseEntity<StudentResponse> addStudent(
            @RequestBody @Valid StudentRequest student
    ) {
        StudentResponse studentResponse = studentService.addNewStudent(student);
        return ResponseEntity.ok(studentResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponse> editStudent(
            @PathVariable Long id,
            @RequestBody @Valid StudentRequest student
    ) {
        StudentResponse studentResponse = studentService.updateStudent(id, student);

        return ResponseEntity.ok(studentResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> getStudentById(
            @PathVariable Long id
    ) {
        StudentResponse studentResponse = studentService.retrieveStudent(id);
        return ResponseEntity.ok(studentResponse);
    }

    @GetMapping("/{studentId}/courses")
    public ResponseEntity<StudentResponse> getStudentAndHisCoursesById(
            @PathVariable Long studentId
    ) {
        StudentResponse studentResponse = studentService.retrieveStudentAndHisCoursesById(studentId);
        return ResponseEntity.ok(studentResponse);
    }

    @GetMapping()
    public ResponseEntity<List<StudentResponse>> getList()
    {
        List<StudentResponse> listOfStudentsResponse = studentService.retrieveListOfStudents();

        return ResponseEntity.ok(listOfStudentsResponse);
    }

    @GetMapping("/courses/{courseId}")
    public ResponseEntity<List<StudentResponse>> getStudentsByCourseId(
            @PathVariable Long courseId
    ) {
        List<StudentResponse> students = studentService.retrieveStudentsByCourseId(courseId);
        return ResponseEntity.ok(students);
    }

    @PostMapping("/{studentId}/courses/{courseId}")
    public ResponseEntity<String> addStudentToCourse(
            @PathVariable Long studentId,
            @PathVariable Long courseId
    ) {
        boolean added = studentService.addStudentToCourse(studentId, courseId);

        if (added) {
            return ResponseEntity.ok("Student added to course successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Student or course not found.");
        }
    }
}

