package com.pensionfund.application.port.out;

import com.pensionfund.domain.model.Member;

import java.util.List;
import java.util.Optional;

public interface MemberPersistencePort {
    /**
     * Sauvegarde un membre dans la base de données
     * @param member le membre à sauvegarder
     * @return le membre sauvegardé avec son identifiant
     */
    Member save(Member member);

    /**
     * Recherche un membre par son numéro de sécurité sociale
     * @param socialSecurityNumber le numéro de sécurité sociale
     * @return le membre s'il existe
     */
    Optional<Member> findBySocialSecurityNumber(String socialSecurityNumber);

    /**
     * Récupère tous les membres actifs
     * @return la liste des membres actifs
     */
    List<Member> findAllActive();

    /**
     * Supprime un membre
     * @param member le membre à supprimer
     */
    void delete(Member member);
} 