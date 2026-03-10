package com.khatep.teacher.dto;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherUpdateRequestDto {
    private String firstName;
    private String lastName;

    @Email
    private String email;
    private String phoneNumber;
    private String subject;
}
