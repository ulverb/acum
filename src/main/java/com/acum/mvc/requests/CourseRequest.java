package com.acum.mvc.requests;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseRequest {

    @NotBlank(message = "Course name is required")
    private String name;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Hours are required")
    @Min(value = 1, message = "Minimum hours must be greater than 0")
    private Integer hours;

    @NotNull(message = "Maximum number of students is required")
    @Min(value = 1, message = "Minimum number of students must be greater than 0")
    @Max(value = 100, message = "Maximum number of students is 100")
    private Integer maxStudents;
}

