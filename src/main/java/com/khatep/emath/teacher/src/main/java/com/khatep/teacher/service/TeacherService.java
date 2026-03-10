package com.khatep.teacher.service;

import com.khatep.teacher.dto.TeacherRequestDto;
import com.khatep.teacher.dto.TeacherResponseDto;
import com.khatep.teacher.dto.TeacherUpdateRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;

public interface TeacherService {
    void create(TeacherRequestDto teacherRequestDto);

    TeacherResponseDto getById(Long id);

    TeacherResponseDto getByEmail(String email);

    Page<TeacherResponseDto> getAll(int page, int size);

    void update(Long id, TeacherUpdateRequestDto teacherUpdateRequestDto);

    void delete(Long id);
}
