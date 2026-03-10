package com.khatep.teacher.repository;

import com.khatep.teacher.entity.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Optional<Teacher> findByEmail(String email);

    Page<Teacher> findAll(Pageable pageable);

    @Modifying
    @Transactional
    @Query("DELETE FROM Teacher t WHERE t.id = :id")
    int deleteByIdReturningCount(@Param("id") Long id);
}
