package com.khatep.teacher.service;

import com.khatep.teacher.dto.TeacherResponseDto;
import com.khatep.teacher.entity.Teacher;
import com.khatep.teacher.exceptions.business.TeacherNotFoundException;
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
import java.util.UUID;

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
    void shouldReturnTeacherResponseDto_WhenTeacherExists() {
        Teacher teacher = Teacher.builder()
                .id(UUID.fromString("9b3e2c4a-7f1d-4c6a-8a52-3f9e1d7b6c21"))
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

        when(teacherRepository.findById(UUID.fromString("9b3e2c4a-7f1d-4c6a-8a52-3f9e1d7b6c21"))).thenReturn(Optional.of(teacher));
        when(teacherMapper.toTeacherResponseDto(teacher)).thenReturn(dto);
        assertEquals(dto, teacherService.getById(UUID.fromString("9b3e2c4a-7f1d-4c6a-8a52-3f9e1d7b6c21")));
    }

    @Test
    void shouldThrowException_WhenTeacherNotFound() {
        when(teacherRepository.findById(UUID.fromString("c8f4a1d9-2e6b-4b73-9c5f-0a8d3e2f7b54"))).thenReturn(Optional.empty());
        assertThrows(TeacherNotFoundException.class, () -> teacherService.getById(UUID.fromString("c8f4a1d9-2e6b-4b73-9c5f-0a8d3e2f7b54")));
    }
}
