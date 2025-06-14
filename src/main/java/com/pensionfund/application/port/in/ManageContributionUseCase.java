package com.pensionfund.application.port.in;

import com.pensionfund.domain.model.Contribution;
import com.pensionfund.domain.model.Member;

public interface ManageContributionUseCase {
    /**
     * Ajoute une contribution pour un membre
     * @param member le membre concerné
     * @param contribution la contribution à ajouter
     * @return le membre mis à jour avec sa nouvelle contribution
     */
    Member addContribution(Member member, Contribution contribution);

    /**
     * Calcule le total des contributions d'un membre pour une année donnée
     * @param member le membre concerné
     * @param year l'année
     * @return le montant total des contributions
     */
    double calculateYearlyContributions(Member member, int year);
} 