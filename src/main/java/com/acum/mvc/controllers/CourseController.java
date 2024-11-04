package com.acum.mvc.controllers;


import com.acum.mvc.responses.CourseResponse;
import com.acum.services.Interfaces.ICourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final ICourseService courseService;

    @GetMapping("/getCoursesAndEnrolledStudents")
    public ResponseEntity<List<CourseResponse>> listCoursesWithStudentsCount()
    {
        // Display courses with the count of enrolled students
        List<CourseResponse> courses = courseService.listCoursesAndEnrolledStudents();

        return ResponseEntity.ok(courses);
    }



    @GetMapping("/{studentId}")
    public ResponseEntity<List<CourseResponse>> getCoursesByStudentId(
        @PathVariable Long studentId
    ) {
        List<CourseResponse> courses = courseService.getCoursesByStudentId(studentId);

        return ResponseEntity.ok(courses);
    }
}
