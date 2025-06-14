package com.pensionfund.application.service;

import com.pensionfund.application.port.in.GetMemberUseCase;
import com.pensionfund.application.port.in.RegisterMemberUseCase;
import com.pensionfund.application.port.out.MemberPersistencePort;
import com.pensionfund.domain.model.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService implements RegisterMemberUseCase, GetMemberUseCase {

    private final MemberPersistencePort memberPersistencePort;

    @Override
    public Member register(Member member) {
        // Vérification que le numéro de sécurité sociale n'existe pas déjà
        if (memberPersistencePort.findBySocialSecurityNumber(member.getSocialSecurityNumber()).isPresent()) {
            throw new IllegalArgumentException("Un membre avec ce numéro de sécurité sociale existe déjà");
        }

        // Validation des données du membre
        validateMember(member);

        // Enregistrement du membre
        return memberPersistencePort.save(member);
    }

    @Override
    public Optional<Member> getBySocialSecurityNumber(String socialSecurityNumber) {
        return memberPersistencePort.findBySocialSecurityNumber(socialSecurityNumber);
    }

    @Override
    public List<Member> getAllActiveMembers() {
        return memberPersistencePort.findAllActive();
    }

    private void validateMember(Member member) {
        if (member.getFirstname() == null || member.getFirstname().trim().isEmpty()) {
            throw new IllegalArgumentException("Le prénom est obligatoire");
        }
        if (member.getLastname() == null || member.getLastname().trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom est obligatoire");
        }
        if (member.getSocialSecurityNumber() == null || member.getSocialSecurityNumber().trim().isEmpty()) {
            throw new IllegalArgumentException("Le numéro de sécurité sociale est obligatoire");
        }
        if (member.getBirthdate() == null) {
            throw new IllegalArgumentException("La date de naissance est obligatoire");
        }
        if (member.getAffiliationDate() == null) {
            throw new IllegalArgumentException("La date d'affiliation est obligatoire");
        }
    }
} 