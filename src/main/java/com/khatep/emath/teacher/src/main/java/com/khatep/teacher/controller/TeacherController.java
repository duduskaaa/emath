package com.khatep.teacher.controller;

import com.khatep.teacher.dto.OnCreate;
import com.khatep.teacher.dto.OnUpdate;
import com.khatep.teacher.dto.TeacherRequestDto;
import com.khatep.teacher.dto.TeacherResponseDto;
import com.khatep.teacher.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/teachers")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<TeacherResponseDto>>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size)
    {
        return ResponseEntity.ok(teacherService.getAll(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherResponseDto> getTeacherById(@PathVariable Long id) {
        return ResponseEntity.ok(teacherService.getById(id));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<TeacherResponseDto> getTeacherByEmail(@PathVariable String email) {
        return ResponseEntity.ok(teacherService.getByEmail(email));
    }

    @PostMapping
    public ResponseEntity<Void> create(@Validated(OnCreate.class) @RequestBody TeacherRequestDto dto) {
        teacherService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @Validated(OnUpdate.class) @RequestBody TeacherRequestDto dto) {
        teacherService.update(id, dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        teacherService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
