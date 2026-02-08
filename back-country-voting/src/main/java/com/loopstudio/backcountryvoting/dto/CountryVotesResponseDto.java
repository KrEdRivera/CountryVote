package com.loopstudio.backcountryvoting.dto;

public record CountryVotesResponseDto(
		String country,
		String capital,
        String region,
        String subregion,
        Long votes
		) {}
