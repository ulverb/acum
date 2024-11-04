package com.acum.services;

import com.acum.dal.models.CourseModel;
import com.acum.dal.models.StudentCourseModel;
import com.acum.dal.models.StudentModel;
import com.acum.dal.repositories.CourseRepository;
import com.acum.dal.repositories.StudentCourseRepository;
import com.acum.dal.repositories.StudentRepository;
import com.acum.mvc.requests.StudentRequest;
import com.acum.mvc.responses.CourseResponse;
import com.acum.mvc.responses.StudentResponse;
import com.acum.services.Interfaces.IStudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service("StudentService")
public class StudentService implements IStudentService
{
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final StudentCourseRepository studentCourseRepository;

    private final static ModelMapper modelMapper = new ModelMapper();

    @Override
    public StudentResponse retrieveStudent(Long studentId)
    {
        Optional<StudentModel> studentOpt = studentRepository.findById(studentId);

        if(studentOpt.isEmpty()) {
            log.warn("User with id={} doesn't exists.", studentId);
            return null; // or throw exception
        }

        return modelMapper.map(studentOpt, StudentResponse.class);
    }

    @Override
    public StudentResponse addNewStudent(StudentRequest student)
    {
        StudentModel studentModel = modelMapper.map(student, StudentModel.class);
        StudentModel savedStudent = studentRepository.save(studentModel);

        return modelMapper.map(savedStudent, StudentResponse.class);
    }

    @Override
    public List<StudentResponse> retrieveListOfStudents()
    {
        // Pagination can be added here.
        List<StudentModel> students = studentRepository.findAll();

        return students.stream()
                .map(student -> modelMapper.map(student, StudentResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public StudentResponse updateStudent(Long id, StudentRequest student)
    {
        Optional<StudentModel> studentOpt = studentRepository.findById(id);

        if (studentOpt.isEmpty()) {
            log.warn("Cannot update. Student with id={} doesn't exist.", id);
            return null; // or throw exception
        }

        StudentModel updateStudent = studentOpt.get();
        updateStudent.setFirstName(student.getFirstName());
        updateStudent.setLastName(student.getLastName());
        updateStudent.setEmail(student.getEmail());
        updateStudent.setDateOfBirth(student.getDateOfBirth());
        updateStudent.setFieldOfStudy(student.getFieldOfStudy());

        StudentModel updatedStudent = studentRepository.save(updateStudent);

        return modelMapper.map(updatedStudent, StudentResponse.class);
    }

    @Override
    @Transactional
    public boolean addStudentToCourse(Long studentId, Long courseId)
    {
        Optional<StudentModel> studentOpt = studentRepository.findById(studentId);
        Optional<CourseModel> courseOpt = courseRepository.findById(courseId);

        if (studentOpt.isEmpty() || courseOpt.isEmpty()) {
            return false; // Return false if either student or course is not found
        }

        StudentCourseModel studentCourseModel = new StudentCourseModel();
        studentCourseModel.setStudentId(studentOpt.get().getId());
        studentCourseModel.setCourseId(courseOpt.get().getId());

        studentCourseRepository.save(studentCourseModel);
        return true;
    }

    @Override
    public List<StudentResponse> retrieveStudentsByCourseId(Long courseId)
    {
        List<StudentModel> students = studentRepository.findStudentsByCourseId(courseId);

        return students.stream()
                .map(student -> modelMapper.map(student, StudentResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public StudentResponse retrieveStudentAndHisCoursesById(Long studentId)
    {
        Optional<StudentModel> studentOpt = studentRepository.findById(studentId);

        if(studentOpt.isEmpty()) {
            log.warn("Student with id={} doesn't exists.", studentId);
            return null; // or throw exception
        }

        List<CourseModel> courses = courseRepository.findCoursesByStudentId(studentId);

        List<CourseResponse> courseResponseList = courses.stream()
                .map(course -> modelMapper.map(course, CourseResponse.class))
                .collect(Collectors.toList());

        StudentResponse studentResponse = modelMapper.map(studentOpt.get(), StudentResponse.class);

        studentResponse.setStudentCourses(courseResponseList);

        return studentResponse;
    }
}

