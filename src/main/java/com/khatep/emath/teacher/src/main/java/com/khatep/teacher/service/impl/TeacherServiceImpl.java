package com.khatep.teacher.service.impl;

import com.khatep.teacher.exceptions.business.TeacherNotFound;
import com.khatep.teacher.repository.TeacherRepository;
import com.khatep.teacher.dto.TeacherRequestDto;
import com.khatep.teacher.dto.TeacherResponseDto;
import com.khatep.teacher.entity.Teacher;
import com.khatep.teacher.mapper.TeacherMapper;
import com.khatep.teacher.service.TeacherService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

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
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new TeacherNotFound("Teacher with id " + id + " not found"));
        return teacherMapper.toTeacherResponseDto(teacher);
    }

    @Override
    public List<TeacherResponseDto> getAll() {
        List<TeacherResponseDto> teachers = teacherRepository.findAll()
                .stream()
                .map(teacherMapper::toTeacherResponseDto)
                .toList();

        return teachers;
    }

    @Override
    @Transactional
    public void update(Long id, TeacherRequestDto teacherRequestDto) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow();
        teacher = teacherMapper.updateTeacherFromDto(teacherRequestDto, teacher);
        teacherRepository.save(teacher);
    }

    @Override
    public void delete(Long id) {
        teacherRepository.deleteById(id);
    }
}
