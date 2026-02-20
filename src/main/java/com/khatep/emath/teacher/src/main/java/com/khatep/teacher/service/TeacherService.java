package com.khatep.teacher.service;

import com.khatep.teacher.dto.TeacherRequestDto;
import com.khatep.teacher.dto.TeacherResponseDto;

import java.util.List;

public interface TeacherService {
    void create(TeacherRequestDto teacherRequestDto);

    TeacherResponseDto getById(Long id);

    TeacherResponseDto getByEmail(String email);

    List<TeacherResponseDto> getAll();

    void update(Long id, TeacherRequestDto teacherRequestDto);

    void delete(Long id);
}
