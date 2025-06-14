package com.pensionfund.adapter.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "members")
@Getter
@Setter
public class MemberJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String lastname;

    @Column(nullable = false)
    private LocalDate birthdate;

    @Column(name = "social_security_number", nullable = false, unique = true)
    private String socialSecurityNumber;

    @Column(name = "affiliation_date", nullable = false)
    private LocalDate affiliationDate;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ContributionJpaEntity> contributions = new ArrayList<>();
} 