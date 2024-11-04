package com.acum.services.Interfaces;

import com.acum.mvc.requests.StudentRequest;
import com.acum.mvc.responses.StudentResponse;

import java.util.List;

public interface IStudentService
{
    StudentResponse retrieveStudent(Long id);

    StudentResponse addNewStudent(StudentRequest student);

    List<StudentResponse> retrieveListOfStudents();

    StudentResponse updateStudent(Long id, StudentRequest student);

    boolean addStudentToCourse(Long studentId, Long courseId);

    List<StudentResponse> retrieveStudentsByCourseId(Long courseId);

    StudentResponse retrieveStudentAndHisCoursesById(Long studentId);
}
