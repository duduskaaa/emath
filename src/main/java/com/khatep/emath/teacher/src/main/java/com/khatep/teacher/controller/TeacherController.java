package com.khatep.teacher.controller;

import com.khatep.teacher.dto.TeacherRequestDto;
import com.khatep.teacher.dto.TeacherResponseDto;
import com.khatep.teacher.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<TeacherResponseDto> getTeacherById(@PathVariable Long id) {
        return ResponseEntity.ok(teacherService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody TeacherRequestDto dto) {
        teacherService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody TeacherRequestDto dto) {
        teacherService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        teacherService.delete(id);

    }
}
