package com.acum.mvc.controllers;

import com.acum.mvc.requests.LecturerRequest;
import com.acum.mvc.responses.LecturerResponse;
import com.acum.services.Interfaces.ILecturerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/lecturers")
public class LecturerController
{

    private final ILecturerService lecturerService;

    @PostMapping("/add")
    public ResponseEntity<LecturerResponse> addLecturer(
            @RequestBody @Valid LecturerRequest lecture
    ) {
        LecturerResponse lecturerResponse = lecturerService.addNewLecturer(lecture);
        return ResponseEntity.ok(lecturerResponse);
    }

    @PostMapping("/{lecturerId}/courses/{courseId}")
    public ResponseEntity<String> addLecturerToCourse(
            @PathVariable Long lecturerId,
            @PathVariable Long courseId) {

        boolean added = lecturerService.addLecturerToCourse(lecturerId, courseId);

        if (added) {
            return ResponseEntity.ok("Lecturer added to course successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Lecturer or course not found.");
        }
    }
}
