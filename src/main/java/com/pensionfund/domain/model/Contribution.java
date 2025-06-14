package com.pensionfund.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Contribution {
    private int year;
    private double amount;
    private Member member;

    public Contribution() {
    }

    public Contribution(int year, double amount, Member member) {
        this.year = year;
        this.amount = amount;
        this.member = member;
    }

    public boolean isValid() {
        return year > 0 && amount > 0;
    }
} 