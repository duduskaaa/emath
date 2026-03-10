package com.khatep.teacher.service;

import com.khatep.teacher.dto.TeacherRequestDto;
import com.khatep.teacher.dto.TeacherResponseDto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;

import java.util.List;

public interface TeacherService {
    void create(TeacherRequestDto teacherRequestDto);

    TeacherResponseDto getById(Long id);

    TeacherResponseDto getByEmail(String email);

    PagedModel<EntityModel<TeacherResponseDto>> getAll(int page, int size);

    void update(Long id, TeacherRequestDto teacherRequestDto);

    void delete(Long id);
}
