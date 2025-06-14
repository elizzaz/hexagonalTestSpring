package com.pensionfund.adapter.in.web;

import com.pensionfund.adapter.in.web.dto.MemberDto;
import com.pensionfund.application.port.in.GetMemberUseCase;
import com.pensionfund.application.port.in.RegisterMemberUseCase;
import com.pensionfund.domain.model.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final RegisterMemberUseCase registerMemberUseCase;
    private final GetMemberUseCase getMemberUseCase;

    @PostMapping
    public ResponseEntity<MemberDto> registerMember(@RequestBody MemberDto memberDto) {
        Member member = mapToDomain(memberDto);
        Member registeredMember = registerMemberUseCase.register(member);
        return ResponseEntity.ok(mapToDto(registeredMember));
    }

    @GetMapping("/{socialSecurityNumber}")
    public ResponseEntity<MemberDto> getMember(@PathVariable String socialSecurityNumber) {
        return getMemberUseCase.getBySocialSecurityNumber(socialSecurityNumber)
                .map(member -> ResponseEntity.ok(mapToDto(member)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<MemberDto>> getAllActiveMembers() {
        List<MemberDto> members = getMemberUseCase.getAllActiveMembers().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(members);
    }

    private Member mapToDomain(MemberDto dto) {
        return Member.builder()
                .firstname(dto.getFirstname())
                .lastname(dto.getLastname())
                .birthdate(dto.getBirthdate())
                .socialSecurityNumber(dto.getSocialSecurityNumber())
                .affiliationDate(dto.getAffiliationDate())
                .build();
    }

    private MemberDto mapToDto(Member member) {
        MemberDto dto = new MemberDto();
        dto.setFirstname(member.getFirstname());
        dto.setLastname(member.getLastname());
        dto.setBirthdate(member.getBirthdate());
        dto.setSocialSecurityNumber(member.getSocialSecurityNumber());
        dto.setAffiliationDate(member.getAffiliationDate());
        return dto;
    }
} 