package com.programmers.baseballticketing.web.domain.match.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.programmers.baseballticketing.domain.match.service.MatchService;
import com.programmers.baseballticketing.web.domain.match.dto.MatchRequestDto;
import com.programmers.baseballticketing.web.domain.match.dto.MatchResponseDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/match")
@RequiredArgsConstructor
public class MatchApiController {

	private final MatchService matchService;

	@PostMapping
	public MatchResponseDto createMatch(@RequestBody MatchRequestDto matchRequestDto) {
		return matchService.createMatch(matchRequestDto);
	}

	@GetMapping("/all")
	public List<MatchResponseDto> getMatches(
		@RequestParam(required = false) LocalDateTime startTime,
		@RequestParam(required = false) String stadiumName,
		@RequestParam(required = false) String team
	) {
		return matchService.getMatchesBy(startTime, stadiumName, team);
	}

	@PatchMapping("/{matchId}")
	public MatchResponseDto updateMatch(@PathVariable Long matchId,
		@RequestBody MatchRequestDto matchRequestDto) {
		return matchService.updateMatch(matchId, matchRequestDto);
	}

	@DeleteMapping("/{matchId}")
	public ResponseEntity deleteMatch(@PathVariable Long matchId) {
		matchService.deleteMatchById(matchId);
		return ResponseEntity.ok().build();
	}
}
