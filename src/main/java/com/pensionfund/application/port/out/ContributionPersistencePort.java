package com.pensionfund.application.port.out;

import com.pensionfund.domain.model.Contribution;
import com.pensionfund.domain.model.Member;

import java.util.List;

public interface ContributionPersistencePort {
    /**
     * Sauvegarde une contribution
     * @param contribution la contribution à sauvegarder
     * @return la contribution sauvegardée
     */
    Contribution save(Contribution contribution);

    /**
     * Récupère toutes les contributions d'un membre
     * @param member le membre concerné
     * @return la liste des contributions
     */
    List<Contribution> findByMember(Member member);

    /**
     * Récupère toutes les contributions d'un membre pour une année donnée
     * @param member le membre concerné
     * @param year l'année
     * @return la liste des contributions
     */
    List<Contribution> findByMemberAndYear(Member member, int year);

    /**
     * Supprime une contribution
     * @param contribution la contribution à supprimer
     */
    void delete(Contribution contribution);
} 