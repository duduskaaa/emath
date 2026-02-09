package com.khatep.teacher.repository;

import com.khatep.teacher.entity.Teacher;
import org.springframework.data.repository.CrudRepository;

public interface TeacherRepository extends CrudRepository<Teacher, Long> {
    Teacher getTeacherById(Long id);
}
