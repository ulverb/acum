package com.acum.services;

import com.acum.dal.models.CourseModel;
import com.acum.dal.repositories.CourseRepository;
import com.acum.dal.repositories.StudentCourseRepository;
import com.acum.mvc.responses.CourseResponse;
import com.acum.services.Interfaces.ICourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service("CourseService")
public class CourseService implements ICourseService
{
    private final CourseRepository courseRepository;
    private final StudentCourseRepository studentCourseRepository;

    private final static ModelMapper mapper = new ModelMapper();

    @Override
    public List<CourseResponse> getCoursesByStudentId(Long studentId)
    {
        List<CourseModel> courses = courseRepository.findCoursesByStudentId(studentId);

        return courses.stream()
                .map(course -> mapper.map(course, CourseResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<CourseResponse> listCoursesAndEnrolledStudents()
    {
        List<CourseModel> courses = courseRepository.findAll();

        if(courses.isEmpty()){
            log.warn("No courses found.");
            return null;
        }

        List<CourseResponse> listResponse = new ArrayList<>();

        for(CourseModel course : courses)
        {
            Integer studentsEnrolled = studentCourseRepository.countEnrolledStudentsByCourseId(course.getId());
            course.setStudentsEnrolled(studentsEnrolled);

            listResponse.add(mapper.map(course, CourseResponse.class));
        }

        return listResponse;
    }
}
