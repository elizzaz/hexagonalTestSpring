package com.pensionfund.adapter.in.web.mapper;

import com.pensionfund.adapter.in.web.dto.MemberDto;
import com.pensionfund.domain.model.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberWebMapper {
    
    public Member toDomain(MemberDto dto) {
        if (dto == null) {
            return null;
        }

        return Member.builder()
                .firstname(dto.getFirstname())
                .lastname(dto.getLastname())
                .birthdate(dto.getBirthdate())
                .socialSecurityNumber(dto.getSocialSecurityNumber())
                .affiliationDate(dto.getAffiliationDate())
                .build();
    }

    public MemberDto toDto(Member member) {
        if (member == null) {
            return null;
        }

        MemberDto dto = new MemberDto();
        dto.setFirstname(member.getFirstname());
        dto.setLastname(member.getLastname());
        dto.setBirthdate(member.getBirthdate());
        dto.setSocialSecurityNumber(member.getSocialSecurityNumber());
        dto.setAffiliationDate(member.getAffiliationDate());
        return dto;
    }
} 