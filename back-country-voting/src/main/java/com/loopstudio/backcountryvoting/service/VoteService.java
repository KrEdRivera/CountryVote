package com.loopstudio.backcountryvoting.service;

import com.loopstudio.backcountryvoting.dto.VoteRequestDto;
import com.loopstudio.backcountryvoting.dto.CountryVotesResponseDto;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VoteService {
	
	void saveVote(VoteRequestDto voteRequestDto);
    //List<CountryVotesResponseDto> getRanking();
	Page<CountryVotesResponseDto> getRanking(Pageable pageable);
    
}

