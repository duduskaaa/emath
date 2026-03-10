package com.khatep.teacher.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherRequestDto {
    @NotBlank(groups = OnCreate.class)
    private String firstName;

    @NotBlank(groups = OnCreate.class)
    private String lastName;

    @NotBlank(groups = OnCreate.class)
    @Email(groups = {OnCreate.class, OnUpdate.class})
    private String email;

    private String phoneNumber;
    private String subject;
}
