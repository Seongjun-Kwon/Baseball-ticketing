package com.programmers.baseballticketing.domain.match.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.programmers.baseballticketing.domain.match.model.Match;
import com.programmers.baseballticketing.domain.match.repository.MatchRepository;
import com.programmers.baseballticketing.web.domain.match.dto.MatchRequestDto;
import com.programmers.baseballticketing.web.domain.match.dto.MatchResponseDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MatchService {

	private final MatchRepository matchRepository;

	public MatchResponseDto createMatch(MatchRequestDto matchRequestDto) {
		Match match = matchRequestDto.toMatch(matchRequestDto);
		Long matchId = matchRepository.save(match);
		MatchResponseDto matchResponseDto = MatchResponseDto.toResponseDto(match);
		matchResponseDto.setId(matchId);
		return matchResponseDto;
	}

	public List<MatchResponseDto> getMatchesBy(LocalDateTime startTime, String stadiumName, String team) {
		List<Match> matches = matchRepository.findBy(startTime, stadiumName, team);
		return matches.stream()
			.map(MatchResponseDto::toResponseDto)
			.collect(Collectors.toList());
	}

	public List<MatchResponseDto> getAllMatches() {
		List<Match> matches = matchRepository.findAll();
		return matches.stream()
			.map(MatchResponseDto::toResponseDto)
			.collect(Collectors.toList());
	}

	public MatchResponseDto updateMatch(Long matchId, MatchRequestDto matchRequestDto) {
		Match match = matchRequestDto.toMatch(matchId, matchRequestDto);
		matchRepository.update(match);
		return MatchResponseDto.toResponseDto(match);
	}

	public void deleteMatchById(Long id) {
		matchRepository.deleteById(id);
	}
}
