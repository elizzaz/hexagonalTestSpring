package com.pensionfund.adapter.persistence.repository;

import com.pensionfund.adapter.persistence.entity.ContributionJpaEntity;
import com.pensionfund.adapter.persistence.entity.MemberJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContributionJpaRepository extends JpaRepository<ContributionJpaEntity, Long> {
    List<ContributionJpaEntity> findByMember(MemberJpaEntity member);
    List<ContributionJpaEntity> findByMemberAndYear(MemberJpaEntity member, int year);
} 