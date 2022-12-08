package com.programmers.baseballticketing.web.domain.match.dto;

import java.time.LocalDateTime;

import com.programmers.baseballticketing.domain.match.model.Match;
import com.programmers.baseballticketing.domain.stadium.model.Stadium;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MatchRequestDto {

	private LocalDateTime startTime;
	private String homeTeamName;
	private String awayTeamName;
	private Integer leftSeatsCount;
	private String stadiumName;

	public Match toMatch(MatchRequestDto matchRequestDto) {
		return new Match(
			matchRequestDto.getStartTime(),
			matchRequestDto.getHomeTeamName(),
			matchRequestDto.getAwayTeamName(),
			matchRequestDto.getLeftSeatsCount(),
			Stadium.getStadiumByName(matchRequestDto.getStadiumName()).getId()
		);
	}

	public Match toMatch(Long matchId, MatchRequestDto matchRequestDto) {
		return new Match(
			matchId,
			matchRequestDto.getStartTime(),
			matchRequestDto.getHomeTeamName(),
			matchRequestDto.getAwayTeamName(),
			matchRequestDto.getLeftSeatsCount(),
			Stadium.getStadiumByName(matchRequestDto.getStadiumName()).getId()
		);
	}
}
