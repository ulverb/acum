package com.acum.dal.repositories;

import com.acum.dal.models.LecturerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LecturerRepository extends JpaRepository<LecturerModel, Long>
{
}
