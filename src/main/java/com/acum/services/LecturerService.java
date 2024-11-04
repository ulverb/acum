package com.acum.services;

import com.acum.dal.models.CourseModel;
import com.acum.dal.models.LecturerCourseModel;
import com.acum.dal.models.LecturerModel;
import com.acum.dal.repositories.CourseRepository;
import com.acum.dal.repositories.LecturerCourseRepository;
import com.acum.dal.repositories.LecturerRepository;
import com.acum.mvc.requests.LecturerRequest;
import com.acum.mvc.responses.LecturerResponse;
import com.acum.services.Interfaces.ILecturerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service("LecturerService")
public class LecturerService implements ILecturerService
{

    private final LecturerCourseRepository lecturerCourseRepository;
    private final LecturerRepository lecturerRepository;
    private final CourseRepository courseRepository;

    private final static ModelMapper mapper = new ModelMapper();


    @Transactional
    public boolean addLecturerToCourse(Long lecturerId, Long courseId)
    {
        // Check if the lecturer and course exist
        Optional<LecturerModel> lecturer = lecturerRepository.findById(lecturerId);
        Optional<CourseModel> course = courseRepository.findById(courseId);

        if (lecturer.isEmpty() || course.isEmpty()) {
            log.warn("Either lecturer={} or course={} is not found.", lecturerId, courseId);

            return false; // Return false if either lecturer or course is not found
        }

        // Create new LecturerCourseModel and save it
        LecturerCourseModel lecturerCourseModel = new LecturerCourseModel();
        lecturerCourseModel.setLecturerId(lecturer.get().getId());
        lecturerCourseModel.setCourseId(course.get().getId());

        lecturerCourseRepository.save(lecturerCourseModel);

        return true;

    }

    @Override
    public LecturerResponse addNewLecturer(LecturerRequest lecture)
    {
        LecturerModel lecturerModel = mapper.map(lecture, LecturerModel.class);
        LecturerModel savedLecturer = lecturerRepository.save(lecturerModel);

        return mapper.map(savedLecturer, LecturerResponse.class);
    }
}
