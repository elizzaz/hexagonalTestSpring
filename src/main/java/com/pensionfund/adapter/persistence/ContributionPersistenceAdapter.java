package com.pensionfund.adapter.persistence;

import com.pensionfund.adapter.persistence.entity.ContributionJpaEntity;
import com.pensionfund.adapter.persistence.entity.MemberJpaEntity;
import com.pensionfund.adapter.persistence.repository.ContributionJpaRepository;
import com.pensionfund.application.port.out.ContributionPersistencePort;
import com.pensionfund.domain.model.Contribution;
import com.pensionfund.domain.model.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ContributionPersistenceAdapter implements ContributionPersistencePort {

    private final ContributionJpaRepository contributionJpaRepository;
    private final ContributionMapper contributionMapper;
    private final MemberMapper memberMapper;

    @Override
    public Contribution save(Contribution contribution) {
        ContributionJpaEntity contributionJpaEntity = contributionMapper.toJpaEntity(contribution);
        ContributionJpaEntity savedEntity = contributionJpaRepository.save(contributionJpaEntity);
        return contributionMapper.toDomainEntity(savedEntity);
    }

    @Override
    public List<Contribution> findByMember(Member member) {
        MemberJpaEntity memberJpaEntity = memberMapper.toJpaEntity(member);
        return contributionJpaRepository.findByMember(memberJpaEntity).stream()
                .map(contributionMapper::toDomainEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<Contribution> findByMemberAndYear(Member member, int year) {
        MemberJpaEntity memberJpaEntity = memberMapper.toJpaEntity(member);
        return contributionJpaRepository.findByMemberAndYear(memberJpaEntity, year).stream()
                .map(contributionMapper::toDomainEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Contribution contribution) {
        contributionJpaRepository.delete(contributionMapper.toJpaEntity(contribution));
    }
} 