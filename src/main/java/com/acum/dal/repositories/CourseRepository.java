package com.acum.dal.repositories;

import com.acum.dal.models.CourseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<CourseModel, Long>
{
    @Query(nativeQuery = true, value = """
        SELECT
                DISTINCT c.*
            FROM students s
            JOIN student_courses sc ON s.id = sc.student_id
            JOIN courses c ON c.id = sc.course_id
            WHERE s.id = :studentId;
    """)
    List<CourseModel> findCoursesByStudentId(
            @Param("studentId") Long studentId
    );
}
