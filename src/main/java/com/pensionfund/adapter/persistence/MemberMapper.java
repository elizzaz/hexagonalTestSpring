package com.pensionfund.adapter.persistence;

import com.pensionfund.adapter.persistence.entity.MemberJpaEntity;
import com.pensionfund.domain.model.Member;
import org.springframework.stereotype.Component;

// convertit entre les entités JPA et les modèles de domaine donc gère la transformation des données dans les deux sens

@Component
public class MemberMapper {
    
    public MemberJpaEntity toJpaEntity(Member member) {
        if (member == null) {
            return null;
        }

        MemberJpaEntity entity = new MemberJpaEntity();
        entity.setFirstname(member.getFirstname());
        entity.setLastname(member.getLastname());
        entity.setBirthdate(member.getBirthdate());
        entity.setSocialSecurityNumber(member.getSocialSecurityNumber());
        entity.setAffiliationDate(member.getAffiliationDate());
        return entity;
    }

    public Member toDomainEntity(MemberJpaEntity entity) {
        if (entity == null) {
            return null;
        }

        return Member.builder()
                .firstname(entity.getFirstname())
                .lastname(entity.getLastname())
                .birthdate(entity.getBirthdate())
                .socialSecurityNumber(entity.getSocialSecurityNumber())
                .affiliationDate(entity.getAffiliationDate())
                .build();
    }
} 