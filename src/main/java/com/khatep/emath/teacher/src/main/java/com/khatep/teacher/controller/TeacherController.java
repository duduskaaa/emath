package com.khatep.teacher.controller;

import com.khatep.teacher.dto.*;
import com.khatep.teacher.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/teachers")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;
    private final PagedResourcesAssembler<TeacherResponseDto> assembler;

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<TeacherResponseDto>>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size)
    {
        Page<TeacherResponseDto> dtoPage = teacherService.getAll(page, size);
        return ResponseEntity.ok(assembler.toModel(dtoPage, EntityModel::of));
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
    public ResponseEntity<Void> create(@Validated @RequestBody TeacherRequestDto dto) {
        teacherService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @Validated @RequestBody TeacherUpdateRequestDto dto) {
        teacherService.update(id, dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        teacherService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
