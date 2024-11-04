package com.acum.dal.repositories;

import com.acum.dal.models.LecturerCourseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LecturerCourseRepository  extends JpaRepository<LecturerCourseModel, Long>
{
}
