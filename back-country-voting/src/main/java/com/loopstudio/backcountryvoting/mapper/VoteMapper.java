package com.loopstudio.backcountryvoting.mapper;

import com.loopstudio.backcountryvoting.dto.CountryResponseDto;
import com.loopstudio.backcountryvoting.model.Vote;

public class VoteMapper {

	private VoteMapper() {}

	public static Vote toEntity(
            String username,
            String email,
            CountryResponseDto country
    ) {
        Vote vote = new Vote();
        vote.setUsername(username);
        vote.setEmail(email);

        // Datos que vienen de la API externa
        vote.setCountry(country.name().common());
        vote.setCapital(country.capital().get(0));
        vote.setRegion(country.region());
        vote.setSubRegion(country.subregion());

        return vote;
    }
	
}
