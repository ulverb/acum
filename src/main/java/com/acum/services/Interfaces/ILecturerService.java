package com.acum.services.Interfaces;

import com.acum.mvc.requests.LecturerRequest;
import com.acum.mvc.responses.LecturerResponse;
import jakarta.validation.Valid;

public interface ILecturerService
{
    boolean addLecturerToCourse(Long lecturerId, Long courseId);

    LecturerResponse addNewLecturer( LecturerRequest lecture);
}
