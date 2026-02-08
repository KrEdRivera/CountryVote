package com.loopstudio.backcountryvoting.client;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.loopstudio.backcountryvoting.dto.CountryResponseDto;


@Service
public class CountryClient {
	private final WebClient webClient;
	
	public CountryClient(WebClient.Builder builder) {
        this.webClient = builder
                .baseUrl("https://restcountries.com/v3.1")
                .build();
    }
	
	public List<CountryResponseDto> getCountry(String country) {
		CountryResponseDto[] response = webClient.get()
                .uri("/name/{country}?fullText=true", country)
                .retrieve()
                .bodyToFlux(CountryResponseDto[].class)
                .blockFirst();
		return response != null ? Arrays.asList(response) : List.of();
    }
}
