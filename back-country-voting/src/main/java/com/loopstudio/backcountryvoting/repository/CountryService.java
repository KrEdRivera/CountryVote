package com.loopstudio.backcountryvoting.repository;

import com.loopstudio.backcountryvoting.dto.CountryResponseDto;

public interface CountryService {

	/**
     * Obtiene la información de un país a partir de su nombre.
     *
     * @param countryName nombre del país
     * @return DTO con la información del país
     */
    CountryResponseDto getCountry(String countryName);	
	
}
