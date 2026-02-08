package com.loopstudio.backcountryvoting.dto;

import java.util.List;

public record CountryResponseDto(
		NameDto name,
        List<String> capital,
        String region,
        String subregion
		) {}
