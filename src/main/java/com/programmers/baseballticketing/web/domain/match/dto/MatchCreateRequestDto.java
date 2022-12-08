package com.programmers.baseballticketing.web.domain.match.dto;

import java.time.LocalDateTime;

import com.programmers.baseballticketing.domain.match.model.Match;
import com.programmers.baseballticketing.domain.stadium.model.Stadium;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MatchCreateRequestDto {

	private LocalDateTime startTime;
	private String homeTeamName;
	private String awayTeamName;
	private Integer leftSeatsCount;
	private String stadiumName;

	public Match toMatch(MatchCreateRequestDto matchCreateRequestDto) {
		return new Match(
			matchCreateRequestDto.getStartTime(),
			matchCreateRequestDto.getHomeTeamName(),
			matchCreateRequestDto.getAwayTeamName(),
			matchCreateRequestDto.getLeftSeatsCount(),
			Stadium.getStadiumByName(matchCreateRequestDto.getStadiumName()).getId()
		);
	}

	public Match toMatch(Long matchId, MatchCreateRequestDto matchCreateRequestDto) {
		return new Match(
			matchId,
			matchCreateRequestDto.getStartTime(),
			matchCreateRequestDto.getHomeTeamName(),
			matchCreateRequestDto.getAwayTeamName(),
			matchCreateRequestDto.getLeftSeatsCount(),
			Stadium.getStadiumByName(matchCreateRequestDto.getStadiumName()).getId()
		);
	}
}
