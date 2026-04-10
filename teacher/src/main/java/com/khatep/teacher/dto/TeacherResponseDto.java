package com.khatep.teacher.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class TeacherResponseDto {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String subject;
    private LocalDate hireDate;
    private boolean active;
}
