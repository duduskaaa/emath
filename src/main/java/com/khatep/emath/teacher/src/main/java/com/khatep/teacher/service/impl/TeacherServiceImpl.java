package com.khatep.teacher.service.impl;

import com.khatep.teacher.exceptions.business.TeacherNotFoundByEmailException;
import com.khatep.teacher.exceptions.business.TeacherNotFoundByIdException;
import com.khatep.teacher.repository.TeacherRepository;
import com.khatep.teacher.dto.TeacherRequestDto;
import com.khatep.teacher.dto.TeacherResponseDto;
import com.khatep.teacher.entity.Teacher;
import com.khatep.teacher.mapper.TeacherMapper;
import com.khatep.teacher.service.TeacherService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    private final TeacherMapper teacherMapper;
    private final TeacherRepository teacherRepository;
    private final PagedResourcesAssembler<TeacherResponseDto> assembler;

    @Override
    public void create(TeacherRequestDto teacherRequestDto) {
        Teacher teacher = teacherMapper.toTeacher(teacherRequestDto);

        teacherRepository.save(teacher);
    }

    @Override
    public TeacherResponseDto getById(Long id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new TeacherNotFoundByIdException("Teacher with id " + id + " not found"));
        return teacherMapper.toTeacherResponseDto(teacher);
    }

    @Override
    public TeacherResponseDto getByEmail(String email) {
        Teacher teacher = teacherRepository.findByEmail(email)
                .orElseThrow(() -> new TeacherNotFoundByEmailException("Teacher with email " +email+ " not found"));
        return teacherMapper.toTeacherResponseDto(teacher);
    }

    @Override
    public PagedModel<EntityModel<TeacherResponseDto>> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<TeacherResponseDto> dtoPage = teacherRepository.findAll(pageable)
                .map(teacherMapper::toTeacherResponseDto);
        return assembler.toModel(dtoPage, EntityModel::of);
    }

    @Override
    @Transactional
    public void update(Long id, TeacherRequestDto teacherRequestDto) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new TeacherNotFoundByIdException("Teacher with id " +id+ " not found"));
        teacherMapper.updateTeacherFromDto(teacherRequestDto, teacher);
    }

    @Override
    public void delete(Long id) {
        if (!teacherRepository.existsById(id))
            throw new TeacherNotFoundByIdException("Teacher with id+ "+id+ "not found");
        teacherRepository.deleteById(id);
    }
}
