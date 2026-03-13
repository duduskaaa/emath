package com.khatep.teacher.service;

import com.khatep.teacher.dto.TeacherResponseDto;
import com.khatep.teacher.entity.Teacher;
import com.khatep.teacher.mapper.TeacherMapper;
import com.khatep.teacher.repository.TeacherRepository;
import com.khatep.teacher.service.impl.TeacherServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class TeacherServiceTest {
    @Mock
    TeacherRepository teacherRepository;

    @Mock
    TeacherMapper teacherMapper;

    @InjectMocks
    TeacherServiceImpl teacherService;

    @Test
    void getByIdTest() {
        Teacher teacher = Teacher.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .email("john@test.com")
                .build();

        TeacherResponseDto dto = new TeacherResponseDto();
        dto.setFirstName(teacher.getFirstName());
        dto.setLastName(teacher.getLastName());
        dto.setEmail(teacher.getEmail());
        dto.setPhoneNumber(teacher.getPhoneNumber());
        dto.setActive(teacher.isActive());

        when(teacherRepository.findById(1L)).thenReturn(Optional.of(teacher));
        when(teacherMapper.toTeacherResponseDto(teacher)).thenReturn(dto);
        assertEquals(dto, teacherService.getById(1L));
    }
}
