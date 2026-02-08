package com.loopstudio.backcountryvoting.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.loopstudio.backcountryvoting.dto.CountryVotesResponseDto;
import com.loopstudio.backcountryvoting.model.Vote;

public interface VoteRepository extends JpaRepository<Vote,UUID> {

	boolean existsByEmail(String email); 
	
	@Query(nativeQuery = true, value = "SELECT country, capital, region, sub_region AS subregion, COUNT(*) AS votes FROM votes GROUP BY country, capital, region, sub_region ORDER BY votes DESC")
	Page<CountryVotesResponseDto> countVotesByCountry(Pageable pageable);
	
	
	
	
	
}
