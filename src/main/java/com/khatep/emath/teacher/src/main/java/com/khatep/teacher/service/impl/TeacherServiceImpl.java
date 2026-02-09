package com.khatep.teacher.service.impl;

import com.khatep.teacher.repository.TeacherRepository;
import com.khatep.teacher.dto.TeacherRequestDto;
import com.khatep.teacher.dto.TeacherResponseDto;
import com.khatep.teacher.entity.Teacher;
import com.khatep.teacher.mapper.TeacherMapper;
import com.khatep.teacher.service.TeacherService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    private final TeacherMapper teacherMapper;
    private final TeacherRepository teacherRepository;

    @Override
    public void create(TeacherRequestDto teacherRequestDto) {
        Teacher teacher = teacherMapper.toTeacher(teacherRequestDto);
        teacherRepository.save(teacher);
    }

    @Override
    public TeacherResponseDto getById(Long id) {
        Teacher teacher = teacherRepository.getTeacherById(id);
        return teacherMapper.toResponseDto(teacher);
    }

    @Override
    @Transactional
    public void update(Long id) {

    }

    @Override
    public void delete(Long id) {
        teacherRepository.deleteById(id);
    }
}
