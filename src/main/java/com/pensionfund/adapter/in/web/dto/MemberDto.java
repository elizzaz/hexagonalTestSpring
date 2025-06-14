package com.pensionfund.adapter.in.web.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MemberDto {
    private String firstname;
    private String lastname;
    private LocalDate birthdate;
    private String socialSecurityNumber;
    private LocalDate affiliationDate;
} 