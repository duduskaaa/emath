package com.khatep.teacher.controller;

import com.khatep.teacher.dto.TeacherRequestDto;
import com.khatep.teacher.dto.TeacherResponseDto;
import com.khatep.teacher.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/teachers")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;

    @GetMapping
    public List<TeacherResponseDto> getAll() {
        return teacherService.getAll();
    }

    @GetMapping("/{id}")
    public TeacherResponseDto getTeacherById(@PathVariable Long id) {
        return teacherService.getById(id);
    }

    @PostMapping
    public void create(@RequestBody TeacherRequestDto dto) {
        teacherService.create(dto);
    }

    @PutMapping("/{id}")
    //TODO: Add update methods for other fields
    public void update(@PathVariable Long id) {
        teacherService.update(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        teacherService.delete(id);

    }
}
