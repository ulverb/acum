package com.acum.dal.repositories;

import com.acum.dal.models.StudentCourseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentCourseRepository extends JpaRepository<StudentCourseModel, Long>
{

    @Query(nativeQuery = true, value = """
        SELECT COUNT(sc.student_id)
            FROM student_courses sc
            WHERE sc.course_id = :courseId
    """)
    Integer countEnrolledStudentsByCourseId(
        @Param("courseId") Long courseId
    );

}
