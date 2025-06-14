package com.pensionfund.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
public class Member {
    private String firstname;
    private String lastname;
    private LocalDate birthdate;
    private String socialSecurityNumber;
    private LocalDate affiliationDate;
    private List<Contribution> contributions;

    public Member() {
        this.contributions = new ArrayList<>();
    }

    public Member(String firstname, String lastname, LocalDate birthdate, 
                 String socialSecurityNumber, LocalDate affiliationDate, 
                 List<Contribution> contributions) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdate = birthdate;
        this.socialSecurityNumber = socialSecurityNumber;
        this.affiliationDate = affiliationDate;
        this.contributions = contributions != null ? contributions : new ArrayList<>();
    }

    public boolean isActive() {
        return affiliationDate != null;
    }

    public double calculateTotalContributions() {
        return contributions.stream()
                .mapToDouble(Contribution::getAmount)
                .sum();
    }

    public void addContribution(Contribution contribution) {
        if (contribution != null) {
            contributions.add(contribution);
        }
    }
} 