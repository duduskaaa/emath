package com.khatep.teacher.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherUpdateRequestDto {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String subject;
}
