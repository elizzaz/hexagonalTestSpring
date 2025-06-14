package com.pensionfund.adapter.persistence;

import com.pensionfund.adapter.persistence.entity.ContributionJpaEntity;
import com.pensionfund.domain.model.Contribution;
import com.pensionfund.domain.model.Member;
import org.springframework.stereotype.Component;

@Component
public class ContributionMapper {
    
    private final MemberMapper memberMapper;

    public ContributionMapper(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    public ContributionJpaEntity toJpaEntity(Contribution contribution) {
        if (contribution == null) {
            return null;
        }

        ContributionJpaEntity entity = new ContributionJpaEntity();
        entity.setYear(contribution.getYear());
        entity.setAmount(contribution.getAmount());
        entity.setMember(memberMapper.toJpaEntity(contribution.getMember()));
        return entity;
    }

    public Contribution toDomainEntity(ContributionJpaEntity entity) {
        if (entity == null) {
            return null;
        }

        return Contribution.builder()
                .year(entity.getYear())
                .amount(entity.getAmount())
                .member(memberMapper.toDomainEntity(entity.getMember()))
                .build();
    }
} 