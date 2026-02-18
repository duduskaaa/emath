package com.khatep.teacher.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherResponseDto {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Boolean active;
}
