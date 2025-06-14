package com.pensionfund.application.port.in;

import com.pensionfund.domain.model.Member;

public interface RegisterMemberUseCase {
    /**
     * Enregistre un nouveau membre dans le système
     * @param member le membre à enregistrer
     * @return le membre enregistré avec son identifiant
     */
    Member register(Member member);
} 