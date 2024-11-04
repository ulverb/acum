package com.acum.mvc.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseResponse
{
    private Long id;
    private String name;
    private String description;
    private Integer hours;
    private Integer maxStudents;
    private Integer studentsEnrolled;
}
