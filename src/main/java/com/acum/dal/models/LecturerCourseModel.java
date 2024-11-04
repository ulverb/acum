package com.acum.dal.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.ZonedDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lecturer_courses",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"lecturer_id", "course_id"})},
        indexes = {
                @Index(name = "idx_lecturer_courses_course_id", columnList = "course_id"),
                @Index(name = "idx_lecturer_courses_lecture_id", columnList = "lecturer_id")
        })
public class LecturerCourseModel
{
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "lecturer_id", nullable = false)
        private Long lecturerId;

        @Column(name = "course_id", nullable = false)
        private Long courseId;

        @CreationTimestamp
        @Column(name = "created_at", columnDefinition = "TIMESTAMP WITH TIME ZONE")
        private ZonedDateTime createdAt;

        @UpdateTimestamp
        @Column(name = "updated_at", columnDefinition = "TIMESTAMP WITH TIME ZONE")
        private ZonedDateTime updatedAt;
}
