package com.pensionfund.adapter.persistence;

import com.pensionfund.adapter.persistence.entity.MemberJpaEntity;
import com.pensionfund.adapter.persistence.repository.MemberJpaRepository;
import com.pensionfund.application.port.out.MemberPersistencePort;
import com.pensionfund.domain.model.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// fait le pont entre le domaine et l'infrastructure

@Component
@RequiredArgsConstructor
public class MemberPersistenceAdapter implements MemberPersistencePort {

    private final MemberJpaRepository memberJpaRepository;
    private final MemberMapper memberMapper;

    @Override
    public Member save(Member member) {
        MemberJpaEntity memberJpaEntity = memberMapper.toJpaEntity(member);
        MemberJpaEntity savedEntity = memberJpaRepository.save(memberJpaEntity);
        return memberMapper.toDomainEntity(savedEntity);
    }

    @Override
    public Optional<Member> findBySocialSecurityNumber(String socialSecurityNumber) {
        return memberJpaRepository.findBySocialSecurityNumber(socialSecurityNumber)
                .map(memberMapper::toDomainEntity);
    }

    @Override
    public List<Member> findAllActive() {
        return memberJpaRepository.findAll().stream()
                .map(memberMapper::toDomainEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Member member) {
        memberJpaRepository.delete(memberMapper.toJpaEntity(member));
    }
} 