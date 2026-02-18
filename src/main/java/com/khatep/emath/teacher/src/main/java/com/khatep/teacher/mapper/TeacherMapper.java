package com.khatep.teacher.mapper;

import com.khatep.teacher.dto.TeacherRequestDto;
import com.khatep.teacher.dto.TeacherResponseDto;
import com.khatep.teacher.entity.Teacher;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface TeacherMapper {
    TeacherResponseDto toTeacherResponseDto(Teacher teacher);

    Teacher toTeacher(TeacherRequestDto teacherRequestDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Teacher updateTeacherFromDto(TeacherRequestDto teacherRequestDto, @MappingTarget Teacher teacher);
}
