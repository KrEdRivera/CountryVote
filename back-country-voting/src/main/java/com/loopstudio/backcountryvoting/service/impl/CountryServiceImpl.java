package com.loopstudio.backcountryvoting.service.impl;


import com.loopstudio.backcountryvoting.client.CountryClient;
import com.loopstudio.backcountryvoting.dto.CountryResponseDto;
import com.loopstudio.backcountryvoting.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService  {

	private final CountryClient countryClient;
	
	public CountryServiceImpl(CountryClient countryClient) {
        this.countryClient = countryClient;
    }
	
	@Override
    public CountryResponseDto getCountry(String countryName) {
        List<CountryResponseDto> countries = countryClient.getCountry(countryName);

        if (countries == null || countries.isEmpty()) {
            throw new IllegalArgumentException("País no encontrado: " + countryName);
        }

        // Retornamos siempre el primer resultado
        return countries.get(0);
    }
	
}
