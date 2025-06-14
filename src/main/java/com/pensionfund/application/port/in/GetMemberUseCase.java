package com.pensionfund.application.port.in;

import com.pensionfund.domain.model.Member;

import java.util.List;
import java.util.Optional;

public interface GetMemberUseCase {
    /**
     * Récupère un membre par son numéro de sécurité sociale
     * @param socialSecurityNumber le numéro de sécurité sociale
     * @return le membre s'il existe
     */
    Optional<Member> getBySocialSecurityNumber(String socialSecurityNumber);

    /**
     * Récupère tous les membres actifs
     * @return la liste des membres actifs
     */
    List<Member> getAllActiveMembers();
} 