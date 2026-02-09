package com.khatep.teacher.mapper;

import com.khatep.teacher.dto.TeacherRequestDto;
import com.khatep.teacher.dto.TeacherResponseDto;
import com.khatep.teacher.entity.Teacher;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TeacherMapper {
    TeacherResponseDto toResponseDto(Teacher teacher);

    Teacher toTeacher(TeacherRequestDto teacherRequestDto);
}
