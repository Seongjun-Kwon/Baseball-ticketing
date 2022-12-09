package com.programmers.baseballticketing.domain.match.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.programmers.baseballticketing.domain.match.model.Match;
import com.programmers.baseballticketing.domain.match.repository.MatchRepository;
import com.programmers.baseballticketing.web.domain.match.dto.MatchCreateRequestDto;
import com.programmers.baseballticketing.web.domain.match.dto.MatchResponseDto;
import com.programmers.baseballticketing.web.domain.match.dto.MatchSearchRequestDto;
import com.programmers.baseballticketing.web.domain.match.dto.MatchUpdateRequestDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MatchService {

	private final MatchRepository matchRepository;

	public MatchResponseDto createMatch(MatchCreateRequestDto matchCreateRequestDto) {
		Match match = matchCreateRequestDto.toMatch(matchCreateRequestDto);
		Long matchId = matchRepository.save(match);
		MatchResponseDto matchResponseDto = MatchResponseDto.toResponseDto(match);
		matchResponseDto.setId(matchId);
		return matchResponseDto;
	}

	public List<MatchResponseDto> getMatchesBy(MatchSearchRequestDto matchSearchRequestDto) {
		List<Match> matches = matchRepository.findBy(
			matchSearchRequestDto.getStartTime(),
			matchSearchRequestDto.getStadiumName(),
			matchSearchRequestDto.getTeam()
		);
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

	public MatchResponseDto updateMatch(Long matchId, MatchUpdateRequestDto matchUpdateRequestDto) {
		Match match = matchRepository.findById(matchId);
		Match updatedMatch = new Match(
			matchId,
			matchUpdateRequestDto.getStartTime(),
			match.getHomeTeamName(),
			match.getAwayTeamName(),
			matchUpdateRequestDto.getLeftSeatsCount(),
			match.getStadiumId()
		);
		matchRepository.update(updatedMatch);
		return MatchResponseDto.toResponseDto(updatedMatch);
	}

	public void deleteMatchById(Long id) {
		matchRepository.deleteById(id);
	}
}
