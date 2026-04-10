package com.khatep.teacher.service;

import com.khatep.teacher.dto.TeacherRequestDto;
import com.khatep.teacher.dto.TeacherResponseDto;
import com.khatep.teacher.dto.TeacherUpdateRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface TeacherService {
    UUID create(TeacherRequestDto teacherRequestDto);

    TeacherResponseDto getById(UUID id);

    TeacherResponseDto getByEmail(String email);

    Page<TeacherResponseDto> getAll(Pageable pageable);

    void update(UUID id, TeacherUpdateRequestDto teacherUpdateRequestDto);

    void delete(UUID id);
}
