package com.loopstudio.backcountryvoting.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

import com.loopstudio.backcountryvoting.dto.CountryVotesResponseDto;
import com.loopstudio.backcountryvoting.dto.VoteRequestDto;
import com.loopstudio.backcountryvoting.service.VoteService;


import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/votes")
@Tag( name = "Votes", description = "Endpoints para registrar votos y consultar rankings por país" )
public class VoteController {
	private final VoteService voteService;

    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    /**
     * Endpoint para registrar un voto
     */
    @Operation(
            summary = "Registrar un voto",
            description = "Registra un voto para un país. El correo debe ser único."
        )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Voto registrado correctamente",
            content = @Content(
                schema = @Schema(implementation = String.class)
            )
        ),
        @ApiResponse(
            responseCode = "409",
            description = "El correo ya está registrado"
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Datos inválidos en la solicitud"
        )
    })
    @PostMapping
    public ResponseEntity<String> vote(@RequestBody VoteRequestDto voteRequestDto) {
        voteService.saveVote(voteRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Your vote was successfully submitted");
        		//ok("Your vote was succesfully submitted");
    }

    /**
     * Endpoint para obtener ranking de votos por país
     */
    @Operation(
            summary = "Obtener ranking de votos por país",
            description = "Devuelve el ranking de países ordenado por cantidad de votos"
        )
        @ApiResponses({
            @ApiResponse(
                responseCode = "200",
                description = "Ranking obtenido correctamente",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(
                        implementation = CountryVotesResponseDto.class
                    )
                )
            )
        })
    @GetMapping("/ranking")
    public ResponseEntity<Page<CountryVotesResponseDto>> getRanking(@PageableDefault(size = 10) Pageable pageable) {
    	Page<CountryVotesResponseDto> ranking = voteService.getRanking(pageable);
        return ResponseEntity.ok(ranking);
    }
}
