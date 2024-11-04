package com.acum.sevices;

import com.acum.mvc.responses.CourseResponse;
import com.acum.services.CourseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CourseServiceTest
{
    @Autowired
    private CourseService courseService;

    @Test
    public void getCoursesByStudentId() {
        List<CourseResponse> courseResponseList = courseService.getCoursesByStudentId(1L);
        assertNotNull(courseResponseList);
        assertEquals(4, courseResponseList.size());
    }

    @Test
    public void listCoursesAndEnrolledStudents() {
        List<CourseResponse> courseResponseList = courseService.listCoursesAndEnrolledStudents();
        assertNotNull(courseResponseList);
        assertTrue(courseResponseList.size() > 0);
        assertEquals(1, courseResponseList.get(3).getStudentsEnrolled());
    }

}
