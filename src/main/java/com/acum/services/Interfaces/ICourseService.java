package com.acum.services.Interfaces;

import com.acum.mvc.responses.CourseResponse;

import java.util.List;

public interface ICourseService
{
    List<CourseResponse> getCoursesByStudentId(Long studentId);

    List<CourseResponse> listCoursesAndEnrolledStudents();
}
