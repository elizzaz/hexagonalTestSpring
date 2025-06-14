package com.pensionfund.adapter.in.web;

import com.pensionfund.adapter.in.web.dto.ContributionDto;
import com.pensionfund.application.port.in.ManageContributionUseCase;
import com.pensionfund.domain.model.Contribution;
import com.pensionfund.domain.model.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contributions")
@RequiredArgsConstructor
public class ContributionController {

    private final ManageContributionUseCase manageContributionUseCase;

    @PostMapping
    public ResponseEntity<ContributionDto> addContribution(@RequestBody ContributionDto contributionDto) {
        Member member = Member.builder()
                .socialSecurityNumber(contributionDto.getMemberSocialSecurityNumber())
                .build();

        Contribution contribution = Contribution.builder()
                .year(contributionDto.getYear())
                .amount(contributionDto.getAmount())
                .member(member)
                .build();

        Member updatedMember = manageContributionUseCase.addContribution(member, contribution);
        return ResponseEntity.ok(mapToDto(contribution));
    }

    @GetMapping("/{socialSecurityNumber}/{year}")
    public ResponseEntity<Double> getYearlyContributions(
            @PathVariable String socialSecurityNumber,
            @PathVariable int year) {
        Member member = Member.builder()
                .socialSecurityNumber(socialSecurityNumber)
                .build();

        double total = manageContributionUseCase.calculateYearlyContributions(member, year);
        return ResponseEntity.ok(total);
    }

    private ContributionDto mapToDto(Contribution contribution) {
        ContributionDto dto = new ContributionDto();
        dto.setYear(contribution.getYear());
        dto.setAmount(contribution.getAmount());
        dto.setMemberSocialSecurityNumber(contribution.getMember().getSocialSecurityNumber());
        return dto;
    }
} 