package com.acum.sevices;

import com.acum.mvc.requests.StudentRequest;
import com.acum.mvc.responses.StudentResponse;
import com.acum.services.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class StudentServiceTests
{

    @Autowired
    private StudentService studentService;

    @Test
    public void testRetrieveStudentAndHisCoursesById() {
        StudentResponse student = studentService.retrieveStudentAndHisCoursesById(1l);
        assertNotNull(student);
        assertEquals(4, student.getStudentCourses().size());
    }

    @Test
    public void testGetStudentByIdWithoutCoursesById() {
        StudentResponse student = studentService.retrieveStudent(2L);
        assertNotNull(student);
        assertEquals("jane.smith@example.com", student.getEmail());
        assertNull(student.getStudentCourses());
    }

    @Test
    public void testGetStudentById_StudentNotFound() {
        StudentResponse student = studentService.retrieveStudent(100L);
        assertNull(student);

    }

    @Test
    public void testAddNewStudent() {
        StudentRequest newStudentRequest = StudentRequest.builder()
                .firstName("New")
                .lastName("Student")
                .email(LocalTime.now() + "@soft.com")
                .dateOfBirth(LocalDate.of(1980,5,14))
                .fieldOfStudy("Computer Science")
                .build();
        StudentResponse addedStudent = studentService.addNewStudent(newStudentRequest);
        assertNotNull(addedStudent);
        StudentResponse retrievedStudent = studentService.retrieveStudent(addedStudent.getId());
        assertNotNull(retrievedStudent);

        assertEquals(newStudentRequest.getEmail(), retrievedStudent.getEmail());

    }

    @Test
    public void testEditStudentById() {
        StudentRequest studentRequest = StudentRequest.builder()
                .firstName("John")
                .lastName("Doe")
                .email("newemail@soft.com")
                .dateOfBirth(LocalDate.of(2000,5,14))
                .fieldOfStudy("Computer Science")
                .build();
        studentService.updateStudent(1L, studentRequest);
        StudentResponse updatedStudent = studentService.retrieveStudent(1L);

        assertNotNull(updatedStudent);
        assertEquals(studentRequest.getEmail(), updatedStudent.getEmail());

    }

    @Test
    public void testGetStudents() {
        List<StudentResponse> students = studentService.retrieveListOfStudents();
        assertNotNull(students);
        assertTrue(students.size() > 0);

    }

    @Test
    public void addStudentToCourse () {
        //Add new student
        StudentRequest newStudentRequest = StudentRequest.builder()
                .firstName("New")
                .lastName("Student")
                .email(LocalTime.now() + "@soft.com")
                .dateOfBirth(LocalDate.of(1980,5,14))
                .fieldOfStudy("Computer Science")
                .build();
        StudentResponse addedStudent = studentService.addNewStudent(newStudentRequest);
        assertNotNull(addedStudent);
        StudentResponse retrievedStudent = studentService.retrieveStudent(addedStudent.getId());
        assertNotNull(retrievedStudent);

        assertEquals(newStudentRequest.getEmail(), retrievedStudent.getEmail());

        // Connect new student to course

        StudentResponse student = studentService.retrieveStudentAndHisCoursesById(retrievedStudent.getId());
        assertNotNull(student);
        assertEquals(0, student.getStudentCourses().size());

        boolean isAdded = studentService.addStudentToCourse(retrievedStudent.getId(), 1L);
        assertTrue(isAdded);

        student = studentService.retrieveStudentAndHisCoursesById(retrievedStudent.getId());

        assertNotNull(student);
        assertEquals(1, student.getStudentCourses().size());

    }
}

