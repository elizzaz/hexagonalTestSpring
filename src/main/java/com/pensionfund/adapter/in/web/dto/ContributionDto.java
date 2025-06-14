package com.pensionfund.adapter.in.web.dto;

import lombok.Data;

@Data
public class ContributionDto {
    private int year;
    private double amount;
    private String memberSocialSecurityNumber;
} 