package com.khatep.teacher.controller;

import com.khatep.teacher.dto.*;
import com.khatep.teacher.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
            @PageableDefault(size = 20, sort = "id", direction = Sort.Direction.ASC)
            Pageable pageable
    )
    {
        Page<TeacherResponseDto> dtoPage = teacherService.getAll(pageable);
        return ResponseEntity.ok(assembler.toModel(dtoPage, EntityModel::of));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherResponseDto> getTeacherById(@PathVariable Long id) {
        return ResponseEntity.ok(teacherService.getById(id));
    }

    @GetMapping(params = "email")
    public ResponseEntity<TeacherResponseDto> getTeacherByEmail(@RequestParam String email) {
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
