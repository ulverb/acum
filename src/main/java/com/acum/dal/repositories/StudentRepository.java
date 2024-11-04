package com.acum.dal.repositories;

import com.acum.dal.models.StudentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<StudentModel, Long>
{
    @Query(nativeQuery = true, value = """
        SELECT
                DISTINCT s.*
            FROM courses c
            JOIN student_courses sc ON c.id = sc.course_id
            JOIN students s ON s.id = sc.student_id
            WHERE c.id = :courseId
    """)
    List<StudentModel> findStudentsByCourseId(
            @Param("courseId") Long courseId
    );
}
