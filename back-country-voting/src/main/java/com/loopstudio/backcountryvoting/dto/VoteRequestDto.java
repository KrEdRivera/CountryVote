package com.loopstudio.backcountryvoting.dto;

public record VoteRequestDto(
		String username,
        String email,
        String country,
        String capital,
        String region,
        String subRegion
        ) {}
