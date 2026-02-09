package com.khatep.teacher.service;

import com.khatep.teacher.dto.TeacherRequestDto;
import com.khatep.teacher.dto.TeacherResponseDto;

public interface TeacherService {
    void create(TeacherRequestDto teacherDto);

    TeacherResponseDto getById(Long id);

    void update(Long id);

    void delete(Long id);
}
