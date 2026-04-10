package com.khatep.teacher.service.impl;

import com.khatep.teacher.dto.TeacherUpdateRequestDto;
import com.khatep.teacher.exceptions.business.TeacherEmailAlreadyExistsException;
import com.khatep.teacher.exceptions.business.TeacherNotFoundException;
import com.khatep.teacher.repository.TeacherRepository;
import com.khatep.teacher.dto.TeacherRequestDto;
import com.khatep.teacher.dto.TeacherResponseDto;
import com.khatep.teacher.entity.Teacher;
import com.khatep.teacher.mapper.TeacherMapper;
import com.khatep.teacher.service.TeacherService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TeacherServiceImpl implements TeacherService {
    private final TeacherMapper teacherMapper;
    private final TeacherRepository teacherRepository;

    @Override
    @Transactional
    public UUID create(TeacherRequestDto teacherRequestDto) {
        Teacher teacher = teacherMapper.toTeacher(teacherRequestDto);
        String email = teacher.getEmail();

        if (teacherRepository.existsByEmail(email)) {
            throw new TeacherEmailAlreadyExistsException("Teacher with email " + email + " already exists");
        }
        teacherRepository.save(teacher);
        return teacher.getId();
    }

    @Override
    public TeacherResponseDto getById(UUID id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new TeacherNotFoundException("Teacher with id " + id + " not found"));
        return teacherMapper.toTeacherResponseDto(teacher);
    }

    @Override
    public TeacherResponseDto getByEmail(String email) {
        Teacher teacher = teacherRepository.findByEmail(email)
                .orElseThrow(() -> new TeacherNotFoundException("Teacher with email " +email+ " not found"));
        return teacherMapper.toTeacherResponseDto(teacher);
    }

    @Override
    public Page<TeacherResponseDto> getAll(Pageable pageable) {
        return teacherRepository.findAll(pageable)
                .map(teacherMapper::toTeacherResponseDto);
    }

    @Override
    @Transactional

    public void update(UUID id, TeacherUpdateRequestDto teacherUpdateRequestDto) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new TeacherNotFoundException("Teacher with id " +id+ " not found"));
        teacherMapper.updateTeacherFromDto(teacherUpdateRequestDto, teacher);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        int deleted = teacherRepository.deleteByIdReturningCount(id);
        if (deleted == 0)
            throw new TeacherNotFoundException("Teacher with id " + id + " not found");
    }
}
