package com.loopstudio.backcountryvoting.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;

import com.loopstudio.backcountryvoting.client.CountryClient;
import com.loopstudio.backcountryvoting.dto.CountryResponseDto;
import com.loopstudio.backcountryvoting.dto.VoteRequestDto;
import com.loopstudio.backcountryvoting.dto.CountryVotesResponseDto;
import com.loopstudio.backcountryvoting.exception.EmailAlreadyRegisteredException;
import com.loopstudio.backcountryvoting.mapper.VoteMapper;
import com.loopstudio.backcountryvoting.model.Vote;
import com.loopstudio.backcountryvoting.repository.VoteRepository;
import com.loopstudio.backcountryvoting.service.CountryService;
import com.loopstudio.backcountryvoting.service.VoteService;

@Service
public class VoteServiceImpl implements VoteService {
	
	private final VoteRepository voteRepository;
    private final CountryService countryService;
    private final CountryClient countryClient;
    
    public VoteServiceImpl(VoteRepository voteRepository, CountryService countryService, CountryClient countryClient) {
        this.voteRepository = voteRepository;
        this.countryService = countryService;
        this.countryClient = countryClient;
    }

	@Override
	public void saveVote(VoteRequestDto voteRequestDto) {
		// TODO Auto-generated method stub
		if (voteRepository.existsByEmail(voteRequestDto.email())) {
            throw new EmailAlreadyRegisteredException(voteRequestDto.email());
        }
		
		//List<CountryResponseDto> countryInfo = countryClient.getCountry(voteRequestDto.country());
		if (voteRequestDto.email() != null && !voteRequestDto.email().isBlank()
		        && voteRequestDto.country() != null && !voteRequestDto.country().isBlank()) {
			
			Vote vote = new Vote();
			vote.setEmail(voteRequestDto.email());
			vote.setCountry(voteRequestDto.country());
			
			
			vote.setUsername(valorONa(voteRequestDto.username()));
			vote.setCapital(valorONa(voteRequestDto.capital()));
			vote.setRegion(valorONa(voteRequestDto.region()));
			vote.setSubRegion(valorONa(voteRequestDto.subRegion()));
			
			voteRepository.save(vote);
		}else {
			throw new IllegalArgumentException("Email y country son obligatorios");
		}
		
	}

	@Override
	public Page<CountryVotesResponseDto> getRanking(Pageable pageable) {
		// TODO Auto-generated method stub
		return voteRepository.countVotesByCountry(pageable);
	}
	
	private String valorONa(String valor) {
	    return (valor == null || valor.isBlank()) ? "NA" : valor;
	}

}
