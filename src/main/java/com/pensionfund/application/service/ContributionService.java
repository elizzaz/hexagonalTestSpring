package com.pensionfund.application.service;

import com.pensionfund.application.port.in.ManageContributionUseCase;
import com.pensionfund.application.port.out.ContributionPersistencePort;
import com.pensionfund.application.port.out.MemberPersistencePort;
import com.pensionfund.domain.model.Contribution;
import com.pensionfund.domain.model.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ContributionService implements ManageContributionUseCase {

    private final ContributionPersistencePort contributionPersistencePort;
    private final MemberPersistencePort memberPersistencePort;

    @Override
    public Member addContribution(Member member, Contribution contribution) {
        // Vérification que le membre existe
        Member existingMember = memberPersistencePort.findBySocialSecurityNumber(member.getSocialSecurityNumber())
                .orElseThrow(() -> new IllegalArgumentException("Membre non trouvé"));

        // Validation de la contribution
        validateContribution(contribution);

        // Association de la contribution au membre
        contribution.setMember(existingMember);

        // Sauvegarde de la contribution
        contributionPersistencePort.save(contribution);

        // Mise à jour du membre avec la nouvelle contribution
        existingMember.addContribution(contribution);
        return memberPersistencePort.save(existingMember);
    }

    @Override
    public double calculateYearlyContributions(Member member, int year) {
        // Vérification que le membre existe
        Member existingMember = memberPersistencePort.findBySocialSecurityNumber(member.getSocialSecurityNumber())
                .orElseThrow(() -> new IllegalArgumentException("Membre non trouvé"));

        // Récupération des contributions de l'année
        return contributionPersistencePort.findByMemberAndYear(existingMember, year)
                .stream()
                .mapToDouble(Contribution::getAmount)
                .sum();
    }

    private void validateContribution(Contribution contribution) {
        if (contribution.getYear() <= 0) {
            throw new IllegalArgumentException("L'année doit être positive");
        }
        if (contribution.getAmount() <= 0) {
            throw new IllegalArgumentException("Le montant doit être positif");
        }
        if (contribution.getMember() == null) {
            throw new IllegalArgumentException("Le membre est obligatoire");
        }
    }
} 